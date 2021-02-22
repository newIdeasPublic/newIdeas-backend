package com.xslgy.common.repository;

import com.xslgy.common.entity.MessageRecord;
import com.xslgy.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRecordRepository extends BaseRepository<MessageRecord, Long> {
}
