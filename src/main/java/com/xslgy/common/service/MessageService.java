package com.xslgy.common.service;

import java.util.Map;

public interface MessageService {

    public boolean sendSmsMessage(String receiver, Integer smsType, Map<String, String> params);

    public boolean sendSmsMessage(String receiver, String content);
}
