package com.xslgy.common.service.impl;

import com.xslgy.common.entity.PublicResource;
import com.xslgy.common.repository.PublicResourceRepository;
import com.xslgy.common.service.PublicResourceService;
import com.xslgy.common.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
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
public class PublicResourceServiceImpl implements PublicResourceService {

    @Resource
    PublicResourceRepository publicResourceRepository;

    @Override
    public PageUtils listPage(String name, String levelOne, String levelTwo, String mobile, Pageable pageable) {
        return new PageUtils(publicResourceRepository.findAll(new Specification<PublicResource>() {
            @Override
            public Predicate toPredicate(Root<PublicResource> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(name)) {
                    criteriaBuilder.like(root.get("name"), "%" + name + "%");
                }
                if (!StringUtils.isEmpty(levelOne)) {
                    criteriaBuilder.equal(root.get("levelOne"), levelOne);
                }
                if (!StringUtils.isEmpty(levelTwo)) {
                    criteriaBuilder.equal(root.get("levelTwo"), levelTwo);
                }
                if (!StringUtils.isEmpty(mobile)) {
                    criteriaBuilder.equal(root.get("mobile"), mobile);
                }
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getGroupRestriction();
            }
        }, pageable));
    }

    @Override
    public PublicResource getById(Long id) {
        return publicResourceRepository.findById(id).orElse(null);
    }

    @Override
    public PublicResource save(PublicResource publicResource) {
        return publicResourceRepository.save(publicResource);
    }

    @Override
    public void deleteById(Long id) {
        publicResourceRepository.deleteById(id);
    }
}
