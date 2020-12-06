package com.xslgy.common.service.impl;

import com.xslgy.common.entity.CmsContent;
import com.xslgy.common.repository.CmsCategoryRepository;
import com.xslgy.common.repository.CmsContentRepository;
import com.xslgy.common.service.CmsContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class CmsContentServiceImpl implements CmsContentService {

    @Resource
    CmsContentRepository cmsContentRepository;
    @Resource
    CmsCategoryRepository cmsCategoryRepository;

    @Override
    public Page<CmsContent> listPageByCode(String code, Pageable pageable) {
        return cmsContentRepository.listPageByCode(code, pageable);
    }

    @Override
    public List<CmsContent> listByCode(String code) {
        return cmsContentRepository.listByCode(code);
    }

    @Override
    public CmsContent save(CmsContent cmsContent) {
        return cmsContentRepository.save(cmsContent);
    }

    @Override
    public void deleteById(Long id) {
        cmsContentRepository.deleteById(id);
    }

    @Override
    public Page<CmsContent> listPageByTitle(String title, Pageable pageable) {
        return cmsContentRepository.findAll(new Specification<CmsContent>() {
            @Override
            public Predicate toPredicate(Root<CmsContent> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(title)) {
                    predicates.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
                }
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getGroupRestriction();
            }
        }, pageable);
    }

    @Override
    public CmsContent getById(Long id) {
        return cmsContentRepository.getOne(id);
    }
}
