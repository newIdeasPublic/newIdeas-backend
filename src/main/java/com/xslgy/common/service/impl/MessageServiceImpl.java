package com.xslgy.common.service.impl;

import com.xslgy.common.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public boolean sendSmsMessage(String receiver, Integer smsType, Map<String, String> params) {
        // 阿里大鱼短信
        return false;
    }

    @Override
    public boolean sendSmsMessage(String receiver, String content) {
        return false;
    }
}
