package com.xslgy.common.service.impl;

import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.xslgy.common.dto.AddVolunteerDTO;
import com.xslgy.common.dto.SearchVolunteerDTO;
import com.xslgy.common.entity.Volunteer;
import com.xslgy.common.repository.VolunteerRepository;
import com.xslgy.common.service.VolunteerService;
import com.xslgy.common.single.SkillSingle;
import com.xslgy.common.utils.PageUtils;
import com.xslgy.common.vo.VolunteerVO;
import com.xslgy.core.exception.XSLGYException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.beans.PropertyDescriptor;
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

    // @Resource
    // private PrivacyUtils privacyUtils;

    @Override
    public void addVolunteer(AddVolunteerDTO addVolunteerDTO) {
        log.info("com.xslgy.common.service.impl.VolunteerServiceImpl.addVolunteer；params: {}", addVolunteerDTO);
        Volunteer volunteer = new Volunteer();
        BeanUtils.copyProperties(addVolunteerDTO, volunteer);
        volunteer.setDeleteFlag(BYTE_ZERO);
        // 将数组转换成字符串
        volunteer.setActivity(String.join(DEFAULT_DELIMITER, addVolunteerDTO.getActivity()));
        volunteer.setFreeDate(String.join(DEFAULT_DELIMITER, addVolunteerDTO.getFreeDate()));
        volunteer.setReason(String.join(DEFAULT_DELIMITER, addVolunteerDTO.getReason()));
        volunteer.setSkill(getSkillStr(addVolunteerDTO.getSkill()));

        // 对手机号和身份证进行加密处理

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
        BeanUtils.copyProperties(addVolunteerDTO, volunteer, getNullPropertyName(addVolunteerDTO));
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
                    .setMobile(volunteer.getMobile())
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
        if (BYTE_ZERO.equals(volunteer.getDeleteFlag())) {
            throw new XSLGYException("志愿者档案不存在");
        }
        List<String> skill = Arrays.asList(volunteer.getSkill().split(DEFAULT_DELIMITER));
        List<String> reason = Arrays.asList(volunteer.getReason().split(DEFAULT_DELIMITER));
        List<String> activity = Arrays.asList(volunteer.getActivity().split(DEFAULT_DELIMITER));
        List<String> freeDate = Arrays.asList(volunteer.getFreeDate().split(DEFAULT_DELIMITER));
        VolunteerVO volunteerVO = new VolunteerVO();
        BeanUtils.copyProperties(volunteer, volunteerVO);
        return volunteerVO
                .setSkill(skill)
                .setReason(reason)
                .setActivity(activity)
                .setFreeDate(freeDate);
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
            // 查询条件中如果包含手机号和身份证，则进行加密并全匹配
            if (Objects.nonNull(searchVolunteerDTO.getMobile())) {
                predicateList.add(criteriaBuilder.equal(root.get("mobile"), searchVolunteerDTO.getMobile()));
            }
            if (Objects.nonNull(searchVolunteerDTO.getIdCard())){
                predicateList.add(criteriaBuilder.equal(root.get("id_card"), searchVolunteerDTO.getIdCard()));
            }
            return criteriaQuery.where(predicateList.toArray(new Predicate[0])).getRestriction();
        };
    }

    /**
     * <pre>获取对象中字段为值为Null的字段列表</pre>
     * <pre>目前是为防止更新信息时，使用BeanUtils.copyProperties时将原有的数据覆盖</pre>
     *
     * @param object 对象
     * @return String[]
     */
    private static String[] getNullPropertyName(Object object) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(object);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        Set<String> emptyName = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            Object propertyValue = beanWrapper.getPropertyValue(propertyDescriptor.getName());
            if (Objects.isNull(propertyValue)) {
                emptyName.add(propertyDescriptor.getName());
            }
        }
        return emptyName.toArray(new String[0]);
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
}
