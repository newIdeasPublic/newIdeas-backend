package com.xslgy.modules.api.action;

import com.xslgy.common.utils.ParamConfig;
import com.xslgy.core.action.BaseController;
import com.xslgy.core.utils.Result;
import com.xslgy.core.utils.ResultUtils;
import com.xslgy.modules.admin.entity.SysUser;
import com.xslgy.modules.admin.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@Slf4j
@RestController
@RequestMapping("api")
public class IndexController extends BaseController {

    @Resource
    ParamConfig paramConfig;
    @Resource
    SysUserService sysUserService;

    @GetMapping("index")
    public Result index() {
        log.info(paramConfig.getCdnUrl());
        SysUser sysUser = new SysUser();
        sysUser.setUsername("admin");
        sysUser.setPassword("admin");
        sysUser.setEmail("admin@admin.com");
        sysUser.setMobile("13000000000");
        sysUserService.save(sysUser);
        return ResultUtils.success();
    }
}
