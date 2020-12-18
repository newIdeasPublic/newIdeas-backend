package com.xslgy.common.service;

import com.xslgy.common.vo.VolunteerVO;

/**
 * @Author : ZhaoHP
 * @Data : 2020/12/17  下午9:48
 * @Description : 志愿者相关服务层接口
 */
public interface VolunteerService {

    /**
     * 添加志愿者档案
     *
     * @param volunteerVO 装载志愿者信息实体
     */
    void addVolunteer(VolunteerVO volunteerVO);

    /**
     * 更新志愿者档案
     *
     * @param volunteerVO 装载志愿者信息实体
     */
    void updateVolunteer(VolunteerVO volunteerVO);
}
