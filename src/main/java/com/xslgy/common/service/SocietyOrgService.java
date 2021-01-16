package com.xslgy.common.service;

import com.xslgy.common.dto.AddSocietyOrgDTO;
import com.xslgy.common.dto.SearchSocietyOrgDTO;
import com.xslgy.common.utils.PageUtils;
import com.xslgy.common.vo.SocietyOrgVO;

/**
 * @Author : 玄机
 * @Data : 2021/1/16  下午8:32
 * @Description : 社会组织相关服务层接口
 */
public interface SocietyOrgService {

    /**
     * 添加社会组织
     *
     * @param addSocietyOrgDTO 社会组织信息载体
     * @return String
     */
    String addSocietyOrg(AddSocietyOrgDTO addSocietyOrgDTO);

    /**
     * 更新社会组织
     *
     * @param addSocietyOrgDTO updateSocietyOrg
     * @return String
     */
    String updateSocietyOrg(AddSocietyOrgDTO addSocietyOrgDTO);

    /**
     * 分页查询社会组织
     *
     * @param searchSocietyOrgDTO 查询条件实体
     * @param pageNum             当前页数
     * @param pageSize            每页显示的记录数
     * @return PageUtils
     */
    PageUtils selectSocietyOrgPage(SearchSocietyOrgDTO searchSocietyOrgDTO, Integer pageNum, Integer pageSize);

    /**
     * 根据ID删除社会组织
     *
     * @param id 主键ID
     * @return String
     */
    String deleteById(Long id);

    /**
     * 根据ID查询详情
     *
     * @param id 主键ID
     * @return SocietyOrgVO
     */
    SocietyOrgVO getDetailById(Long id);
}
