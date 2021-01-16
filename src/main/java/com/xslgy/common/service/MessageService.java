package com.xslgy.common.service;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface MessageService {

    public boolean sendSmsMessage(String receiver, Integer smsType, ObjectNode templateParam);

    public boolean sendSmsMessage(String receiver, String content);

    public boolean sendSmsMessage(String receiver, String sign, String templateCode, ObjectNode templateParam);
}
