package com.xslgy.modules.api.action;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.xslgy.common.utils.ParamConfig;
import com.xslgy.core.action.BaseController;
import com.xslgy.core.utils.Result;
import com.xslgy.core.utils.ResultUtils;
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

    @GetMapping("index")
    public Result index() {
        log.info(paramConfig.getCdnUrl());
        return ResultUtils.success();
    }
}
