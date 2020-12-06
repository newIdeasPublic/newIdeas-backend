package com.xslgy.common.service;

import com.xslgy.common.entity.CmsContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CmsContentService {

    Page<CmsContent> listPageByCode(String code, Pageable pageable);

    List<CmsContent> listByCode(String code);

    CmsContent save(CmsContent cmsContent);

    void deleteById(Long id);

    Page<CmsContent> listPageByTitle(String title, Pageable pageable);

    CmsContent getById(Long id);
}
