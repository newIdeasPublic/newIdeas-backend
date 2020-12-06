package com.xslgy.common.service;

import com.xslgy.common.entity.CmsCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CmsCategoryService {

    Page<CmsCategory> list(String name, Pageable pageable);

    CmsCategory save(CmsCategory cmsCategory);

    void deleteById(Long id);

    CmsCategory getById(Long id);

    CmsCategory getByCode(String code);
}
