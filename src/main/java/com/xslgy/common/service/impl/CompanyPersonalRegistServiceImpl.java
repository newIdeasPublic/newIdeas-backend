package com.xslgy.common.service.impl;

import com.xslgy.common.entity.CompanyPersonalRegist;
import com.xslgy.common.repository.CompanyPersonalRegistRepository;
import com.xslgy.common.service.CompanyPersonalRegistService;
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
public class CompanyPersonalRegistServiceImpl implements CompanyPersonalRegistService {

    @Resource
    CompanyPersonalRegistRepository companyPersonalRegistRepository;

    @Override
    public PageUtils listPage(String name, String mobile, String companyName, String companyCode, Pageable pageable) {
        return new PageUtils(companyPersonalRegistRepository.findAll(new Specification<CompanyPersonalRegist>() {
            @Override
            public Predicate toPredicate(Root<CompanyPersonalRegist> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(name)) {
                    criteriaBuilder.like(root.get("name"), "%" + name + "%");
                }
                if (!StringUtils.isEmpty(mobile)) {
                    criteriaBuilder.equal(root.get("mobile"), mobile);
                }
                if (!StringUtils.isEmpty(companyCode)) {
                    criteriaBuilder.equal(root.get("company_code"), companyCode);
                }
                if (!StringUtils.isEmpty(companyName)) {
                    criteriaBuilder.like(root.get("company_name"), "%" + companyName + "%");
                }
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getGroupRestriction();
            }
        }, pageable));
    }

    @Override
    public CompanyPersonalRegist getById(Long id) {
        return companyPersonalRegistRepository.findById(id).orElse(null);
    }

    @Override
    public CompanyPersonalRegist save(CompanyPersonalRegist companyPersonalRegist) {
        return companyPersonalRegistRepository.save(companyPersonalRegist);
    }

    @Override
    public void deleteById(Long id) {
        companyPersonalRegistRepository.deleteById(id);
    }
}
