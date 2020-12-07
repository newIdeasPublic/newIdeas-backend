package com.xslgy.common.service;

import com.xslgy.common.entity.CmsCategory;
import com.xslgy.common.utils.PageUtils;
import org.springframework.data.domain.Pageable;

public interface CmsCategoryService {

    PageUtils list(String name, Pageable pageable);

    CmsCategory save(CmsCategory cmsCategory);

    void deleteById(Long id);

    CmsCategory getById(Long id);

    CmsCategory getByCode(String code);
}
