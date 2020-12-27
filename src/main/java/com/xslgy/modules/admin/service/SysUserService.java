package com.xslgy.modules.admin.service;

import com.xslgy.common.utils.PageUtils;
import com.xslgy.modules.admin.entity.SysUser;
import org.springframework.data.domain.Pageable;

public interface SysUserService {

    SysUser getByUsername(String username);

    SysUser getByMobile(String mobile);

    PageUtils findAllPage(String username, String mobile, Pageable pageable);

    SysUser save(SysUser sysUser);

    void deleteById(Long id);

    SysUser getById(Long id);

}
