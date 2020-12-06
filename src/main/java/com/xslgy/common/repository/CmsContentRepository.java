package com.xslgy.common.repository;

import com.xslgy.common.entity.CmsContent;
import com.xslgy.core.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmsContentRepository extends BaseRepository<CmsContent, Long> {

    @Query(nativeQuery = true, value = "select t2.* from cms_category t1, cms_content t2 where t1.id = t2.category_id\n" +
            "and t1.code = :code order by t2.order_no desc", countQuery = "select count(*) from (select t2.* from cms_category t1, cms_content t2 where t1.id = t2.category_id\n" +
            "and t1.code = :code order by t2.order_no desc)")
    Page<CmsContent> listPageByCode(@Param("code")String code, Pageable pageable);

    @Query(nativeQuery = true, value = "select t2.* from cms_category t1, cms_content t2 where t1.id = t2.category_id\n" +
            "and t1.code = :code order by t2.order_no desc")
    List<CmsContent> listByCode(@Param("code")String code);
}
