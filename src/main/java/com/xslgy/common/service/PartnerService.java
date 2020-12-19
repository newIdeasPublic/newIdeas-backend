package com.xslgy.common.service;

import java.util.List;

import com.xslgy.common.entity.Partner;

/**
 * @author lamdaer
 * @createTime 2020/12/15
 */
public interface PartnerService {
    Partner save(Partner partner);
    
    List<Partner> list();
}
