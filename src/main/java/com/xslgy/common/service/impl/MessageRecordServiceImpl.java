package com.xslgy.common.service.impl;

import com.xslgy.common.entity.MessageRecord;
import com.xslgy.common.repository.MessageRecordRepository;
import com.xslgy.common.service.MessageRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Service
public class MessageRecordServiceImpl implements MessageRecordService {

    @Resource
    MessageRecordRepository messageRecordRepository;

    @Override
    public MessageRecord save(MessageRecord messageRecord) {
        return messageRecordRepository.save(messageRecord);
    }

    @Override
    public MessageRecord getByTypeAndReceiver(Integer type, String receiver) {
        return messageRecordRepository.getByTypeAndReceiverAndUpdateTimeAfter(type, receiver,
                Date.from(LocalDateTime.now().minusSeconds(60).atZone(ZoneId.systemDefault()).toInstant()));
    }
}
