package com.xslgy.common.repository;

import com.xslgy.common.entity.CmsContent;
import com.xslgy.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsContentRepository extends BaseRepository<CmsContent, Long> {
}
