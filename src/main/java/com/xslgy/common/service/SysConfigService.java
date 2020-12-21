package com.xslgy.common.service;

import com.xslgy.common.entity.SysConfig;
import com.xslgy.common.utils.PageUtils;
import org.springframework.data.domain.Pageable;

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

    PageUtils findByCode(String code, Pageable pageable);
}
