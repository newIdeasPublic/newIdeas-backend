package com.xslgy.common.repository;

import com.xslgy.common.entity.CmsCategory;
import com.xslgy.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsCategoryRepository extends BaseRepository<CmsCategory, Long> {

    CmsCategory getCmsCategoryByCode(String code);
}
