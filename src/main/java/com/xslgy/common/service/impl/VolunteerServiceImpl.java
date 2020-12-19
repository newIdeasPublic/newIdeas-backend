package com.xslgy.common.service.impl;

import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.xslgy.common.dto.AddVolunteerDTO;
import com.xslgy.common.dto.BaseVolunteerDTO;
import com.xslgy.common.entity.Volunteer;
import com.xslgy.common.repository.VolunteerRepository;
import com.xslgy.common.service.VolunteerService;
import com.xslgy.common.utils.PageUtils;
import com.xslgy.common.vo.VolunteerVO;
import com.xslgy.core.exception.XSLGYException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

    private static final byte BYTE_ZERO = (byte) 0;
    private static final byte BYTE_ONE = (byte) 1;

    @Resource
    private VolunteerRepository volunteerRepository;

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
        volunteer.setSkill(String.join(DEFAULT_DELIMITER, addVolunteerDTO.getSkill()));
        // 持久化到数据库
        volunteerRepository.save(volunteer);
    }

    @Override
    public void updateVolunteer(AddVolunteerDTO addVolunteerDTO) {
        log.info("com.xslgy.common.service.impl.VolunteerServiceImpl.updateVolunteer；params: {}", addVolunteerDTO);
        if (Objects.isNull(addVolunteerDTO.getId())) {
            throw new XSLGYException("主键ID不能为空");
        }
        Volunteer volunteer = new Volunteer();
        BeanUtils.copyProperties(addVolunteerDTO, volunteer);
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
            volunteer.setSkill(String.join(DEFAULT_DELIMITER, addVolunteerDTO.getSkill()));
        }
        volunteerRepository.save(volunteer);
    }

    @Override
    public PageUtils getVolunteerPage(BaseVolunteerDTO baseVolunteerDTO, Integer pageNum, Integer pageSize) {
        log.info("com.xslgy.common.service.impl.VolunteerServiceImpl.getVolunteerPage；{}，pageNum: {}；pageSize: {}", baseVolunteerDTO, pageNum, pageSize);
        Page<Volunteer> result = volunteerRepository.findAll(getSpecification(baseVolunteerDTO), PageRequest.of(pageNum - 1, pageSize));
        List<VolunteerVO> volunteerVOList = new ArrayList<>(pageSize);
        for (Volunteer volunteer : result) {
            volunteerVOList.add(new VolunteerVO()
                    .setId(volunteer.getId())
                    .setName(volunteer.getName())
                    .setBirthdate(volunteer.getBirthdate())

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

    private Specification<Volunteer> getSpecification(BaseVolunteerDTO baseVolunteerDTO) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>(8);
            predicateList.add(criteriaBuilder.equal(root.get("deleteFlag"), BYTE_ZERO));
            if (Objects.nonNull(baseVolunteerDTO.getAreaTeam())) {
                predicateList.add(criteriaBuilder.like(root.get("areaTeam"), "%" + baseVolunteerDTO.getAreaTeam() + "%"));
            }
            if (Objects.nonNull(baseVolunteerDTO.getHomeAddr())) {
                predicateList.add(criteriaBuilder.like(root.get("homeAddr"), "%" + baseVolunteerDTO.getHomeAddr() + "%"));
            }
            if (Objects.nonNull(baseVolunteerDTO.getId())) {
                predicateList.add(criteriaBuilder.equal(root.get("id"), baseVolunteerDTO.getId()));
            }
            if (Objects.nonNull(baseVolunteerDTO.getSex())) {
                predicateList.add(criteriaBuilder.equal(root.get("sex"), baseVolunteerDTO.getSex()));
            }
            if (Objects.nonNull(baseVolunteerDTO.getMobile())) {
                predicateList.add(criteriaBuilder.like(root.get("mobile"), "%" + baseVolunteerDTO.getMobile() + "%"));
            }
            if (Objects.nonNull(baseVolunteerDTO.getNation())) {
                predicateList.add(criteriaBuilder.like(root.get("nation"), "%" + baseVolunteerDTO.getNation() + "%"));
            }
            if (Objects.nonNull(baseVolunteerDTO.getName())) {
                predicateList.add(criteriaBuilder.like(root.get("name"), "%" + baseVolunteerDTO.getName() + "%"));
            }
            return criteriaQuery.where(predicateList.toArray(new Predicate[0])).getRestriction();
        };
    }

}
