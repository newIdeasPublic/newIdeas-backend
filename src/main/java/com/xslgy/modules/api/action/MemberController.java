package com.xslgy.modules.api.action;

import com.xslgy.common.service.MemberService;
import com.xslgy.core.action.BaseController;
import com.xslgy.core.utils.Result;
import com.xslgy.core.utils.ResultUtils;
import com.xslgy.modules.api.vo.MemberRegistVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@Api(tags = "前台会员注册、登录、修改密码、重置密码接口")
@RestController
@RequestMapping("api/member")
public class MemberController extends BaseController {

    @Resource
    MemberService memberService;

    @PostMapping("regist")
    public Result regist(@RequestBody MemberRegistVO memberRegistVO) {
        return ResultUtils.success();
    }
}
