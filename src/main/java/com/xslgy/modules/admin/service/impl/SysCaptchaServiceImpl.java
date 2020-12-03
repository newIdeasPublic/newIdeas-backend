package com.xslgy.modules.admin.service.impl;

import com.google.code.kaptcha.Producer;
import com.xslgy.core.exception.XSLGYException;
import com.xslgy.modules.admin.entity.SysCaptcha;
import com.xslgy.modules.admin.repository.SysCaptchaRepository;
import com.xslgy.modules.admin.service.SysCaptchaService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.util.Calendar;

@Service
public class SysCaptchaServiceImpl implements SysCaptchaService {

    @Resource
    Producer producer;
    @Resource
    SysCaptchaRepository sysCaptchaRepository;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            throw new XSLGYException("uuid不能为空");
        }
        SysCaptcha sysCaptcha = sysCaptchaRepository.getSysCaptchaByUuid(uuid);
        if (sysCaptcha == null) {
            sysCaptcha = new SysCaptcha();
        }
        String code = producer.createText();
        sysCaptcha.setUuid(uuid);
        sysCaptcha.setCode(code);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);
        sysCaptcha.setExpireTime(calendar.getTime());
        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        boolean result = false;
        SysCaptcha sysCaptcha = sysCaptchaRepository.getSysCaptchaByUuid(uuid);
        if (sysCaptcha != null) {
            sysCaptchaRepository.deleteSysCaptchaByUuid(uuid);
            if (sysCaptcha.getCode().equalsIgnoreCase(code)) {
                result = true;
            }
        }
        return result;
    }
}
