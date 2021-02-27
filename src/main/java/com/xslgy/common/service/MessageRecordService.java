package com.xslgy.common.service;

import com.xslgy.common.entity.MessageRecord;

public interface MessageRecordService {

    MessageRecord save(MessageRecord messageRecord);

    MessageRecord getByTypeAndReceiver(Integer type, String receiver);

}
