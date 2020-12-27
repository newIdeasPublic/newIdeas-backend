package com.xslgy.common.repository;

import com.xslgy.common.entity.SysConfig;
import com.xslgy.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysConfigRepository extends BaseRepository<SysConfig, Long> {

    SysConfig getSysConfigByCode(String code);
}
