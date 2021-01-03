package com.xslgy.common.repository;

import com.xslgy.common.entity.MessageConfig;
import com.xslgy.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageConfigRepository extends BaseRepository<MessageConfig, Long> {

    MessageConfig getByCode(String code);
}
