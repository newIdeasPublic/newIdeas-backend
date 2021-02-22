package com.xslgy.common.service.impl;

import com.xslgy.common.entity.CmsCategory;
import com.xslgy.common.repository.CmsCategoryRepository;
import com.xslgy.common.service.CmsCategoryService;
import com.xslgy.common.utils.PageUtils;
import com.xslgy.core.exception.XSLGYException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
        if (cmsCategory.getParentId() == null || cmsCategory.getParentId() == 0) {
            // parentId为空的时候，默认为顶级菜单
            cmsCategory.setParentId(0L);
        } else {
            CmsCategory category = getById(cmsCategory.getParentId());
            if (category == null) {
                throw new XSLGYException("上级菜单不存在");
            }
        }
        return cmsCategoryRepository.save(cmsCategory);
    }

    @Override
    public void deleteById(Long id) {
        List<CmsCategory> cmsCategories = getByParentId(id);
        if (!CollectionUtils.isEmpty(cmsCategories)) {
            throw new XSLGYException("该分类下还有子分类，请先删除子分类");
        }
        cmsCategoryRepository.deleteById(id);
    }

    @Override
    public CmsCategory getById(Long id) {
        CmsCategory cmsCategory = cmsCategoryRepository.findById(id).orElse(null);
        if (cmsCategory != null) {
            cmsCategory.setHasChildren(cmsCategoryRepository.countCmsCategoriesByParentId(id) > 0);
        }
        return cmsCategory;
    }

    @Override
    public CmsCategory getByCode(String code) {
        return cmsCategoryRepository.getCmsCategoryByCode(code);
    }

    @Override
    public List<CmsCategory> getByParentId(Long parentId) {
        List<CmsCategory> cmsCategorys = cmsCategoryRepository.findByParentIdOrderByOrderNoDesc(parentId);
        if (!CollectionUtils.isEmpty(cmsCategorys)) {
            cmsCategorys.forEach(cms -> {
               cms.setHasChildren(cmsCategoryRepository.countCmsCategoriesByParentId(cms.getId()) > 0);
            });
        }

        return cmsCategorys;
    }

    @Override
    public CmsCategory hideOrShow(Long id, Integer isShow) {
        CmsCategory category = getById(id);
        category.setIsShow(isShow);
        return save(category);
    }

    @Override
    public List<CmsCategory> findAllCategoryTree() {
        List<CmsCategory> cmsCategories = cmsCategoryRepository.findAll(Sort.by(Sort.Direction.DESC, "orderNo"));
        if (!CollectionUtils.isEmpty(cmsCategories)) {

        }
        return null;
    }
}
