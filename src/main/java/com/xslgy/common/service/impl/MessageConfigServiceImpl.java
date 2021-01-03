package com.xslgy.common.service.impl;

import com.xslgy.common.entity.MessageConfig;
import com.xslgy.common.repository.MessageConfigRepository;
import com.xslgy.common.service.MessageConfigService;
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
public class MessageConfigServiceImpl implements MessageConfigService {

    @Resource
    MessageConfigRepository messageConfigRepository;

    @Override
    public PageUtils findAll(String code, String name, Pageable pageable) {
        return new PageUtils(messageConfigRepository.findAll(new Specification<MessageConfig>() {
            @Override
            public Predicate toPredicate(Root<MessageConfig> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(code)) {
                    predicates.add(criteriaBuilder.like(root.get("code"), "%" + code + "%"));
                }
                if (!StringUtils.isEmpty(name)) {
                    predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
                }
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getGroupRestriction();
            }
        }, pageable));
    }

    @Override
    public MessageConfig getById(Long id) {
        return messageConfigRepository.findById(id).orElse(null);
    }

    @Override
    public MessageConfig getByCode(String code) {
        return messageConfigRepository.getByCode(code);
    }

    @Override
    public MessageConfig save(MessageConfig messageConfig) {
        return messageConfigRepository.save(messageConfig);
    }

    @Override
    public void deleteById(Long id) {
        messageConfigRepository.deleteById(id);
    }
}
