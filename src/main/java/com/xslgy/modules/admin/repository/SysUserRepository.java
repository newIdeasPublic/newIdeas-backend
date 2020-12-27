package com.xslgy.modules.admin.repository;

import com.xslgy.core.repository.BaseRepository;
import com.xslgy.modules.admin.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends BaseRepository<SysUser, Long> {

    SysUser getByUsername(String username);

    SysUser getByMobile(String mobile);
}
