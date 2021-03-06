package com.xslgy.modules.api.action;

import com.xslgy.common.service.MemberService;
import com.xslgy.core.action.BaseController;
import com.xslgy.core.utils.Result;
import com.xslgy.core.utils.ResultUtils;
import com.xslgy.modules.api.vo.MemberLoginVO;
import com.xslgy.modules.api.vo.MemberRegistVO;
import com.xslgy.modules.api.vo.SendSMSVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Api(tags = "前台会员注册、登录、修改密码、重置密码接口")
@RestController
@RequestMapping("api/member")
public class MemberController extends BaseController {

    @Resource
    MemberService memberService;

    @PostMapping("regist")
    public Result regist(@RequestBody MemberRegistVO memberRegistVO) {
        if (StringUtils.isEmpty(memberRegistVO.getUsername()) || StringUtils.isEmpty(memberRegistVO.getPassword())) {
            return ResultUtils.error("用户名或密码不能为空");
        }
        return ResultUtils.success(memberService.regist(memberRegistVO));
    }
    @PostMapping("login")
    public Result login(@RequestBody MemberLoginVO memberLoginVO, HttpServletRequest request) {
        if (StringUtils.isEmpty(memberLoginVO.getUsername()) || StringUtils.isEmpty(memberLoginVO.getPassword())) {
            return ResultUtils.error("用户名或密码不能为空");
        }
        return ResultUtils.success(memberService.login(memberLoginVO, request));
    }
    @PostMapping("sendSms")
    public Result sendSms(@RequestBody SendSMSVO sendSMSVO, HttpServletRequest request) {
        return ResultUtils.success(memberService.sendSms(sendSMSVO, request));
    }
}
