package com.xslgy.common.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.xslgy.common.entity.Partner;
import com.xslgy.common.utils.PageUtils;

/**
 * @author lamdaer
 * @createTime 2020/12/15
 */
public interface PartnerService {
    Partner save(Partner partner);
    
    List<Partner> list();
    
    PageUtils listPage(Pageable pageable);
    
    void deleteById(Long id);
}
