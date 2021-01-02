package com.xslgy.common.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xslgy.common.entity.CompanyPersonalRegist;
import com.xslgy.common.repository.CompanyPersonalRegistRepository;
import com.xslgy.common.service.CompanyPersonalRegistService;
import com.xslgy.common.utils.BeanUtils;
import com.xslgy.common.utils.PageUtils;
import com.xslgy.common.utils.PrivacyUtils;
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
    @Resource
    PrivacyUtils privacyUtils;

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
        CompanyPersonalRegist companyPersonalRegist = companyPersonalRegistRepository.findById(id).orElse(null);
        if (companyPersonalRegist != null && !StringUtils.isEmpty(companyPersonalRegist.getIdCard())) {
            try {
                companyPersonalRegist.setIdCard(privacyUtils.decode(companyPersonalRegist.getIdCard()));
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
            }
        }
        return companyPersonalRegist;
    }

    @Override
    public CompanyPersonalRegist save(CompanyPersonalRegist companyPersonalRegist) {
        // 身份证号加密处理，id为空，是新增，如果不为空，直接加密。如果id不为空，则先将传过来的数据，覆盖到原有实体上，再做加密，保存
        if (companyPersonalRegist.getId() == null) {
            if (!StringUtils.isEmpty(companyPersonalRegist.getIdCard())) {
                try {
                    companyPersonalRegist.setIdCard(privacyUtils.encode(companyPersonalRegist.getIdCard()));
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        } else {
            CompanyPersonalRegist company = getById(companyPersonalRegist.getId());
            if (company != null) {
                BeanUtils.copyPropertiesIgnoreNull(companyPersonalRegist, company);
                companyPersonalRegist = company;
            }
        }
        return companyPersonalRegistRepository.save(companyPersonalRegist);
    }

    @Override
    public void deleteById(Long id) {
        companyPersonalRegistRepository.deleteById(id);
    }
}
