package com.xslgy.common.service.impl;

import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.xslgy.common.entity.Volunteer;
import com.xslgy.common.repository.VolunteerRepository;
import com.xslgy.common.service.VolunteerService;
import com.xslgy.common.vo.VolunteerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author : ZhaoHP
 * @Data : 2020/12/17  下午9:49
 * @Description : 志愿者相关服务层实现
 */
@Slf4j
@Service
public class VolunteerServiceImpl implements VolunteerService {

    private static final String DEFAULT_DELIMITER = ";";

    @Resource
    private VolunteerRepository volunteerRepository;

    @Override
    public void addVolunteer(VolunteerVO volunteerVO) {
        log.info("com.xslgy.common.service.impl.VolunteerServiceImpl.addVolunteer；params: {}", volunteerVO);
        Volunteer volunteer = new Volunteer();
        BeanUtils.copyProperties(volunteerVO, volunteer);
        // 将数组转换成字符串
        volunteer.setActivity(String.join(DEFAULT_DELIMITER, volunteerVO.getActivity()));
        volunteer.setFreeDate(String.join(DEFAULT_DELIMITER, volunteerVO.getFreeDate()));
        volunteer.setReason(String.join(DEFAULT_DELIMITER, volunteerVO.getReason()));
        volunteer.setSkill(String.join(DEFAULT_DELIMITER, volunteerVO.getSkill()));
        // 持久化到数据库
        volunteerRepository.save(volunteer);
    }

    @Override
    public void updateVolunteer(VolunteerVO volunteerVO) {
        log.info("com.xslgy.common.service.impl.VolunteerServiceImpl.updateVolunteer；params: {}", volunteerVO);
        Volunteer volunteer = new Volunteer();
        BeanUtils.copyProperties(volunteerVO, volunteer);
        if (CollectionUtils.isNotEmpty(volunteerVO.getActivity())) {
            volunteer.setActivity(String.join(DEFAULT_DELIMITER, volunteerVO.getActivity()));
        }
        if (CollectionUtils.isNotEmpty(volunteerVO.getFreeDate())) {
            volunteer.setFreeDate(String.join(DEFAULT_DELIMITER, volunteerVO.getFreeDate()));
        }
        if (CollectionUtils.isNotEmpty(volunteerVO.getReason())) {
            volunteer.setReason(String.join(DEFAULT_DELIMITER, volunteerVO.getReason()));
        }
        if (CollectionUtils.isNotEmpty(volunteerVO.getSkill())) {
            volunteer.setSkill(String.join(DEFAULT_DELIMITER, volunteerVO.getSkill()));
        }
        volunteerRepository.save(volunteer);
    }
}
