package com.xslgy.common.service;

import com.xslgy.common.entity.MessageConfig;
import com.xslgy.common.utils.PageUtils;
import org.springframework.data.domain.Pageable;

public interface MessageConfigService {

    PageUtils findAll(String code, String name, Pageable pageable);

    MessageConfig getById(Long id);

    MessageConfig getByCode(String code);

    MessageConfig save(MessageConfig messageConfig);

    void deleteById(Long id);
}
