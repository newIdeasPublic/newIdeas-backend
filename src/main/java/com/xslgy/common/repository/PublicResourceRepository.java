package com.xslgy.common.repository;

import com.xslgy.common.entity.PublicResource;
import com.xslgy.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 公共资源
 */
@Repository
public interface PublicResourceRepository extends BaseRepository<PublicResource, Long> {
}
