package com.xslgy.common.service.impl;

import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.xslgy.common.dto.AddVolunteerDTO;
import com.xslgy.common.dto.SearchVolunteerDTO;
import com.xslgy.common.entity.Volunteer;
import com.xslgy.common.repository.VolunteerRepository;
import com.xslgy.common.service.VolunteerService;
import com.xslgy.common.single.SkillSingle;
import com.xslgy.common.utils.BeanUtils;
import com.xslgy.common.utils.PageUtils;
import com.xslgy.common.utils.PrivacyUtils;
import com.xslgy.common.vo.VolunteerVO;
import com.xslgy.core.exception.XSLGYException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.*;

/**
 * @Author : ZhaoHP
 * @Data : 2020/12/17  下午9:49
 * @Description : 志愿者相关服务层实现
 */
@Slf4j
@Service
public class VolunteerServiceImpl implements VolunteerService {

    private static final String DEFAULT_DELIMITER = ";";
    private static final String SKILL_CATEGORY_CONNECTOR = "-";

    private static final Byte BYTE_ZERO = (byte) 0;
    private static final Byte BYTE_ONE = (byte) 1;

    @Resource
    private VolunteerRepository volunteerRepository;

    @Resource
    private PrivacyUtils privacyUtils;

    @Override
    public void addVolunteer(AddVolunteerDTO addVolunteerDTO) {
        log.info("com.xslgy.common.service.impl.VolunteerServiceImpl.addVolunteer；params: {}", addVolunteerDTO);
        Volunteer volunteer = new Volunteer();
        BeanUtils.copyPropertiesIgnoreNull(addVolunteerDTO, volunteer);
        volunteer.setDeleteFlag(BYTE_ZERO);
        // 将数组转换成字符串
        volunteer.setActivity(String.join(DEFAULT_DELIMITER, addVolunteerDTO.getActivity()));
        volunteer.setFreeDate(String.join(DEFAULT_DELIMITER, addVolunteerDTO.getFreeDate()));
        volunteer.setReason(String.join(DEFAULT_DELIMITER, addVolunteerDTO.getReason()));
        volunteer.setSkill(getSkillStr(addVolunteerDTO.getSkill()));

        // 对手机号和身份证进行加密处理
        String idCard = dateEncode(volunteer.getIdCard()).orElseThrow(() -> new XSLGYException("数据加密失败"));
        volunteer.setIdCard(idCard);


        try {
            // 持久化到数据库
            volunteerRepository.save(volunteer);
        } catch (Exception e) {
            throw new XSLGYException("用户信息已经存在，请勿重复添加");
        }
    }

    @Override
    public void updateVolunteer(AddVolunteerDTO addVolunteerDTO) {
        log.info("com.xslgy.common.service.impl.VolunteerServiceImpl.updateVolunteer；params: {}", addVolunteerDTO);
        if (Objects.isNull(addVolunteerDTO.getId())) {
            throw new XSLGYException("主键ID不能为空");
        }
        Volunteer volunteer = volunteerRepository.findById(addVolunteerDTO.getId()).orElseThrow(() -> new XSLGYException("用户不存在"));
        BeanUtils.copyPropertiesIgnoreNull(addVolunteerDTO, volunteer);
        if (CollectionUtils.isNotEmpty(addVolunteerDTO.getActivity())) {
            volunteer.setActivity(String.join(DEFAULT_DELIMITER, addVolunteerDTO.getActivity()));
        }
        if (CollectionUtils.isNotEmpty(addVolunteerDTO.getFreeDate())) {
            volunteer.setFreeDate(String.join(DEFAULT_DELIMITER, addVolunteerDTO.getFreeDate()));
        }
        if (CollectionUtils.isNotEmpty(addVolunteerDTO.getReason())) {
            volunteer.setReason(String.join(DEFAULT_DELIMITER, addVolunteerDTO.getReason()));
        }
        if (CollectionUtils.isNotEmpty(addVolunteerDTO.getSkill())) {
            volunteer.setSkill(getSkillStr(addVolunteerDTO.getSkill()));
        }
        volunteerRepository.save(volunteer);
    }

    @Override
    public PageUtils getVolunteerPage(SearchVolunteerDTO searchVolunteerDTO, Integer pageNum, Integer pageSize) {
        log.info("com.xslgy.common.service.impl.VolunteerServiceImpl.getVolunteerPage；{}，pageNum: {}；pageSize: {}", searchVolunteerDTO, pageNum, pageSize);
        Page<Volunteer> result = volunteerRepository.findAll(getSpecification(searchVolunteerDTO), PageRequest.of(pageNum - 1, pageSize));
        List<VolunteerVO> volunteerVOList = new ArrayList<>(pageSize);
        for (Volunteer volunteer : result) {
            List<String> freeDate = Arrays.asList(volunteer.getFreeDate().split(DEFAULT_DELIMITER));
            volunteerVOList.add(new VolunteerVO()
                    .setFreeDate(freeDate)
                    .setId(volunteer.getId())
                    .setSex(volunteer.getSex())
                    .setName(volunteer.getName())
                    .setHomeAddr(volunteer.getHomeAddr())
                    .setEducation(volunteer.getEducation())
                    .setProfessional(volunteer.getProfessional())
                    .setCensusRegister(volunteer.getCensusRegister())

            );
        }
        return new PageUtils(volunteerVOList, (int) result.getTotalElements(), result.getSize(), result.getNumber());
    }

    @Override
    public String deleteById(Long id) {
        Volunteer volunteer = volunteerRepository.findById(id).orElseThrow(() -> new XSLGYException("志愿者档案不存在"));
        volunteer.setDeleteFlag(BYTE_ONE);
        volunteerRepository.save(volunteer);
        return "删除成功";
    }

    @Override
    public VolunteerVO getVolunteerVoById(Long id) {
        Volunteer volunteer = volunteerRepository.findById(id).orElseThrow(() -> new XSLGYException("志愿者档案不存在"));
        if (BYTE_ONE.equals(volunteer.getDeleteFlag())) {
            throw new XSLGYException("志愿者档案已删除");
        }
        List<String> skill = Arrays.asList(volunteer.getSkill().split(DEFAULT_DELIMITER));
        List<String> reason = Arrays.asList(volunteer.getReason().split(DEFAULT_DELIMITER));
        List<String> activity = Arrays.asList(volunteer.getActivity().split(DEFAULT_DELIMITER));
        List<String> freeDate = Arrays.asList(volunteer.getFreeDate().split(DEFAULT_DELIMITER));
        VolunteerVO volunteerVO = new VolunteerVO();
        BeanUtils.copyProperties(volunteer, volunteerVO);
        String idCard = dateDecode(volunteer.getIdCard()).orElseThrow(() -> new XSLGYException("获取身份信息出错"));
        String mobile = dateDecode(volunteer.getMobile()).orElseThrow(() -> new XSLGYException("获取手机信息出错"));

        return volunteerVO
                .setSkill(skill).setReason(reason)
                .setIdCard(idCard).setMobile(mobile)
                .setActivity(activity).setFreeDate(freeDate);
    }

    private Specification<Volunteer> getSpecification(SearchVolunteerDTO searchVolunteerDTO) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>(8);
            predicateList.add(criteriaBuilder.equal(root.get("deleteFlag"), BYTE_ZERO));
            if (Objects.nonNull(searchVolunteerDTO.getAreaTeam())) {
                predicateList.add(criteriaBuilder.like(root.get("areaTeam"), "%" + searchVolunteerDTO.getAreaTeam() + "%"));
            }
            if (Objects.nonNull(searchVolunteerDTO.getHomeAddr())) {
                predicateList.add(criteriaBuilder.like(root.get("homeAddr"), "%" + searchVolunteerDTO.getHomeAddr() + "%"));
            }
            if (Objects.nonNull(searchVolunteerDTO.getId())) {
                predicateList.add(criteriaBuilder.equal(root.get("id"), searchVolunteerDTO.getId()));
            }
            if (Objects.nonNull(searchVolunteerDTO.getSex())) {
                predicateList.add(criteriaBuilder.equal(root.get("sex"), searchVolunteerDTO.getSex()));
            }
            if (Objects.nonNull(searchVolunteerDTO.getNation())) {
                predicateList.add(criteriaBuilder.like(root.get("nation"), "%" + searchVolunteerDTO.getNation() + "%"));
            }
            if (Objects.nonNull(searchVolunteerDTO.getName())) {
                predicateList.add(criteriaBuilder.like(root.get("name"), "%" + searchVolunteerDTO.getName() + "%"));
            }
            if (Objects.nonNull(searchVolunteerDTO.getSkillCategory())) {
                predicateList.add(criteriaBuilder.like(root.get("skill"), "%" + searchVolunteerDTO.getSkillCategory() + "%"));
            }
            if (Objects.nonNull(searchVolunteerDTO.getMobile())) {
                predicateList.add(criteriaBuilder.like(root.get("mobile"), searchVolunteerDTO.getMobile() + "%"));
            }
            return criteriaQuery.where(predicateList.toArray(new Predicate[0])).getRestriction();
        };
    }

    private static String getSkillStr(List<String> skill) {
        StringBuilder builder = new StringBuilder();
        Map<String, String> skillMap = SkillSingle.getSkillMap();
        for (String skillItem : skill) {
            String category = skillMap.getOrDefault(skillItem, "其它");
            if ("其它".equals(category)) {
                if (skillItem.startsWith("语言") || skillItem.startsWith("商务")) {
                    category = "专业类";
                }
            }
            builder.append(category).append(SKILL_CATEGORY_CONNECTOR).append(skillItem).append(DEFAULT_DELIMITER);
        }
        return builder.toString();
    }

    /**
     * 加密统一调用
     *
     * @param date 需要加密的数据
     * @return Optional<String>
     */
    private Optional<String> dateEncode(String date) {
        log.info("加密方法调用，需要加密的数据为: {}", date);
        try {
            String encode = privacyUtils.encode(date);
            log.info("加密方法调用结果，加密后的结果: {}", encode);
            return Optional.of(encode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * 解密统一调用
     *
     * @param date 需要解密的数据
     * @return Optional<String>
     */
    private Optional<String> dateDecode(String date) {
        log.info("解密方法调用，需要解密的数据为: {}", date);
        try {
            return Optional.of(privacyUtils.decode(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
