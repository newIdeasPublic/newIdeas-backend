package com.xslgy.modules.admin.entity;

import com.xslgy.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sys_user", indexes = {@Index(name = "sys_user_usernmae_idx", columnList = "username", unique = true),
                                    @Index(name = "sys_user_mobile_idx", columnList = "mobile", unique = true)})
public class SysUser extends BaseEntity {
    private String username;
    private String password;
    private String salt;
    private String mobile;
    private String email;
    private String remark;
}
