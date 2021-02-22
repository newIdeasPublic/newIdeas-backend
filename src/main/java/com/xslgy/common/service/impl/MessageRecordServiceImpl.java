package com.xslgy.common.service.impl;

import com.xslgy.common.repository.MessageRecordRepository;
import com.xslgy.common.service.MessageRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class MessageRecordServiceImpl implements MessageRecordService {

    @Resource
    MessageRecordRepository messageRecordRepository;

}
