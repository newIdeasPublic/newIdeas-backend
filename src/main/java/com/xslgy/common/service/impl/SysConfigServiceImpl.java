package com.xslgy.common.service.impl;

import com.xslgy.common.entity.SysConfig;
import com.xslgy.common.repository.SysConfigRepository;
import com.xslgy.common.service.SysConfigService;
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
public class SysConfigServiceImpl implements SysConfigService {

    @Resource
    SysConfigRepository sysConfigRepository;

    @Override
    public SysConfig save(SysConfig sysConfig) {
        return sysConfigRepository.save(sysConfig);
    }

    @Override
    public void deleteById(Long id) {
        sysConfigRepository.deleteById(id);
    }

    @Override
    public SysConfig getById(Long id) {
        return sysConfigRepository.findById(id).orElse(null);
    }

    @Override
    public SysConfig getByCode(String code) {
        return sysConfigRepository.getSysConfigByCode(code);
    }

    @Override
    public List<SysConfig> findByCode(String code) {
        return sysConfigRepository.findAll(new Specification<SysConfig>() {
            @Override
            public Predicate toPredicate(Root<SysConfig> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(code)) {
                    predicates.add(criteriaBuilder.like(root.get("code"), "%" + code + "%"));
                }
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getGroupRestriction();
            }
        });
    }
}
