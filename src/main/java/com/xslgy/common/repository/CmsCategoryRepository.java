package com.xslgy.common.repository;

import com.xslgy.common.entity.CmsCategory;
import com.xslgy.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmsCategoryRepository extends BaseRepository<CmsCategory, Long> {

    CmsCategory getCmsCategoryByCode(String code);

    List<CmsCategory> findByParentIdOrderByOrderNoDesc(Long parentId);

    int countCmsCategoriesByParentId(Long parentId);
}
