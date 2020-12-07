package com.xslgy.common.service.impl;

import com.xslgy.common.entity.CmsCategory;
import com.xslgy.common.repository.CmsCategoryRepository;
import com.xslgy.common.service.CmsCategoryService;
import com.xslgy.common.utils.PageUtils;
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

@Service
public class CmsCategoryServiceImpl implements CmsCategoryService {

    @Resource
    CmsCategoryRepository cmsCategoryRepository;

    @Override
    public PageUtils list(String name, Pageable pageable) {
        return new PageUtils(cmsCategoryRepository.findAll(new Specification<CmsCategory>() {
            @Override
            public Predicate toPredicate(Root<CmsCategory> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(name)) {
                    predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
                }
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getGroupRestriction();
            }
        }, pageable));
    }

    @Override
    public CmsCategory save(CmsCategory cmsCategory) {
        return cmsCategoryRepository.save(cmsCategory);
    }

    @Override
    public void deleteById(Long id) {
        cmsCategoryRepository.deleteById(id);
    }

    @Override
    public CmsCategory getById(Long id) {
        return cmsCategoryRepository.getOne(id);
    }

    @Override
    public CmsCategory getByCode(String code) {
        return cmsCategoryRepository.getCmsCategoryByCode(code);
    }
}
