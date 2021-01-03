package com.xslgy.common.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xslgy.common.entity.MessageConfig;
import com.xslgy.common.service.MessageConfigService;
import com.xslgy.common.service.MessageService;
import com.xslgy.common.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Value("${newIdeas.sms.accessKeyId}")
    private String accessKeyId;
    @Value("${newIdeas.sms.accessSecret}")
    private String accessSecret;
    @Value("${newIdeas.sms.sign}")
    private String defaultSign;

    @Resource
    MessageConfigService messageConfigService;

    @Override
    public boolean sendSmsMessage(String receiver, Integer smsType, ObjectNode templateParam) {
        boolean result = false;
        // 阿里大鱼短信
        MessageConfig messageConfig = messageConfigService.getByCode(Constant.SMS_TYPE.VERIFICATE_CODE.getCode());
        if (messageConfig != null) {
            result = sendSmsMessage(receiver, null, messageConfig.getCode(), templateParam);
        }
        return result;
    }

    @Override
    public boolean sendSmsMessage(String receiver, String content) {
        return false;
    }

    @Override
    public boolean sendSmsMessage(String receiver, String sign, String templateCode, ObjectNode templateParam) {
        boolean result = false;
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", receiver);
        // 判断签名是否为空，如果为空，则取默认签名
        if (StringUtils.isEmpty(sign)) {
            request.putQueryParameter("SignName", defaultSign);
        } else {
            request.putQueryParameter("SignName", sign);
        }
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", templateParam.toString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            result = true;
            log.info(response.getData());
        } catch (ServerException e) {
            log.error(e.getMessage(), e);
        } catch (ClientException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }
}
