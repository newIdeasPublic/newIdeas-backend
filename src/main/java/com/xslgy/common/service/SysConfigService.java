package com.xslgy.common.service;

import com.xslgy.common.entity.SysConfig;

import java.util.List;

public interface SysConfigService {

    SysConfig save(SysConfig sysConfig);

    void deleteById(Long id);

    SysConfig getById(Long id);

    SysConfig getByCode(String code);

    /**
     * 模糊查询
     * @param code  编码
     * @return
     */
    List<SysConfig> findByCode(String code);
}
