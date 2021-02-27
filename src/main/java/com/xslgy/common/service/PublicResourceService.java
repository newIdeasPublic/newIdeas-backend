package com.xslgy.common.service;

import com.xslgy.common.entity.PublicResource;
import com.xslgy.common.utils.PageUtils;
import org.springframework.data.domain.Pageable;

/**
 * 公共资源
 */
public interface PublicResourceService {

    PageUtils listPage(String name, String levelOne, String levelTwo, String mobile, Pageable pageable);

    PublicResource getById(Long id);

    PublicResource save(PublicResource publicResource);

    void deleteById(Long id);
}
