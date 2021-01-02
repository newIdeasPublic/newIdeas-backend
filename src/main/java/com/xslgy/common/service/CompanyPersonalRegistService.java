package com.xslgy.common.service;

import com.xslgy.common.entity.CompanyPersonalRegist;
import com.xslgy.common.utils.PageUtils;
import org.springframework.data.domain.Pageable;

public interface CompanyPersonalRegistService {

    PageUtils listPage(String name, String mobile, String companyName, String companyCode, Pageable pageable);

    CompanyPersonalRegist getById(Long id);

    CompanyPersonalRegist save(CompanyPersonalRegist companyPersonalRegist);

    void deleteById(Long id);
}
