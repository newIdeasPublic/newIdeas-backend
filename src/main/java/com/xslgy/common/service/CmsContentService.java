package com.xslgy.common.service;

import com.xslgy.common.entity.CmsContent;
import com.xslgy.common.utils.PageUtils;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CmsContentService {

    PageUtils listPageByCode(String code, Pageable pageable);

    List<CmsContent> listByCode(String code);

    CmsContent save(CmsContent cmsContent);

    void deleteById(Long id);

    PageUtils listPageByTitle(String title, Pageable pageable);

    CmsContent getById(Long id);
}
