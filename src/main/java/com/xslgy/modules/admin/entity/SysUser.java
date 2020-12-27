package com.xslgy.modules.admin.entity;

import com.xslgy.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sys_user")
public class SysUser extends BaseEntity {
    private String username;
    private String password;
    private String salt;
    private String mobile;
    private String email;
    private String remark;
}
