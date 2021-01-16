package com.xslgy.modules.admin.service.impl;

import com.xslgy.common.utils.BeanUtils;
import com.xslgy.common.utils.PageUtils;
import com.xslgy.modules.admin.entity.SysUser;
import com.xslgy.modules.admin.repository.SysUserRepository;
import com.xslgy.modules.admin.service.SysUserService;
import com.xslgy.modules.admin.utils.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.UUID;

@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    SysUserRepository sysUserRepository;
    @Value("${newIdeas.defaultAdminPassword}")
    private String defaultAdminPassword;

    @Override
    public SysUser getByUsername(String username) {
        return sysUserRepository.getByUsername(username);
    }

    @Override
    public SysUser getByMobile(String mobile) {
        return sysUserRepository.getByMobile(mobile);
    }

    @Override
    public PageUtils findAllPage(String username, String mobile, Pageable pageable) {
        return new PageUtils(sysUserRepository.findAll(new Specification<SysUser>() {
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(username)) {
                    predicates.add(criteriaBuilder.like(root.get("username"), "%" + username + "%"));
                }
                if (!StringUtils.isEmpty(mobile)) {
                    predicates.add(criteriaBuilder.equal(root.get("mobile"), mobile));
                }
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getGroupRestriction();
            }
        }, pageable));
    }

    @Override
    public SysUser save(SysUser sysUser) {
        // 判断id是否为空，如果为空，新增、如果不为空，判断密码是否有变动
        SysUser result = null;
        if (sysUser.getId() == null) {
            // 新增用户时，保证用户名和手机号都是唯一的
            sysUser.setSalt(UUID.randomUUID().toString());
            if (StringUtils.isEmpty(sysUser.getPassword())) {
                sysUser.setPassword(defaultAdminPassword);
            }
        } else {
            SysUser dbUser = getById(sysUser.getId());
            BeanUtils.copyPropertiesIgnoreNull(sysUser, dbUser);
            sysUser = dbUser;
        }
        // 判断密码
        if (!StringUtils.isEmpty(sysUser.getPassword())) {
            sysUser.setPassword(PasswordUtils.encode(sysUser.getPassword(), sysUser.getSalt()));
        }

        return sysUserRepository.save(sysUser);
    }

    @Override
    public void deleteById(Long id) {
        sysUserRepository.deleteById(id);
    }

    @Override
    public SysUser getById(Long id) {
        return sysUserRepository.findById(id).orElse(null);
    }
}
