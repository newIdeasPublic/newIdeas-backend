package com.xslgy.common.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @Author : ZhaoHP
 * @Data : 2020/12/17  下午10:35
 */
public class MvnConfig  implements WebMvcConfigurer {
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure().failFast(true).buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
