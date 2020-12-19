package com.xslgy.common.service;

import com.xslgy.common.dto.AddVolunteerDTO;
import com.xslgy.common.dto.BaseVolunteerDTO;
import com.xslgy.common.utils.PageUtils;
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
     * @param addVolunteerDTO 装载志愿者信息实体
     */
    void addVolunteer(AddVolunteerDTO addVolunteerDTO);

    /**
     * 更新志愿者档案
     *
     * @param addVolunteerDTO 装载志愿者信息实体
     */
    void updateVolunteer(AddVolunteerDTO addVolunteerDTO);

    /**
     * 分页查询志愿者列表
     *
     * @param baseVolunteerDTO 查询条件实体
     * @param pageNum          每页记录数
     * @param pageSize         当前页数
     * @return Page<Volunteer>
     */
    PageUtils getVolunteerPage(BaseVolunteerDTO baseVolunteerDTO, Integer pageNum, Integer pageSize);

    /**
     * 按照ID逻辑删除对应的档案
     *
     * @param id 主键ID
     * @return String
     */
    String deleteById(Long id);

    /**
     * 根据ID查询详情信息
     *
     * @param id 主键ID
     * @return VolunteerVO
     */
    VolunteerVO getVolunteerVoById(Long id);
}
