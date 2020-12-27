package com.xslgy.modules.admin.action;

import com.xslgy.core.action.BaseController;
import com.xslgy.core.utils.Result;
import com.xslgy.core.utils.ResultUtils;
import com.xslgy.modules.admin.entity.SysUser;
import com.xslgy.modules.admin.service.SysCaptchaService;
import com.xslgy.modules.admin.service.SysUserService;
import com.xslgy.modules.admin.utils.PasswordUtils;
import com.xslgy.modules.admin.vo.LoginVO;
import com.xslgy.modules.admin.vo.SysUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Api(tags = "登录")
@RestController
@RequestMapping("admin/login")
public class LoginController extends BaseController {

    @Resource
    SysCaptchaService sysCaptchaService;
    @Resource
    SysUserService sysUserService;
    @ApiOperation(value = "图形验证码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "图形验证码请求唯一标识", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store,no-cache");
        response.setContentType("image/jpg");
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    @PostMapping("login")
    @ApiOperation("用户登录")
    public Result login(@RequestBody LoginVO loginVO, HttpServletRequest request) {
        SysUserVO result = new SysUserVO();
        // 判断用户名密码是否为空
        if (!StringUtils.isEmpty(loginVO.getUuid()) && !StringUtils.isEmpty(loginVO.getCode()) && !StringUtils.isEmpty(loginVO.getUsername()) || !StringUtils.isEmpty(loginVO.getMobile()) && !StringUtils.isEmpty(loginVO.getPassword()) && !StringUtils.isEmpty(loginVO.getCode())) {
            SysUser sysUser = null;
            if (!StringUtils.isEmpty(loginVO.getUsername())) {
                sysUser = sysUserService.getByUsername(loginVO.getUsername());
            } else {
                sysUser = sysUserService.getByMobile(loginVO.getMobile());
            }
            // 查询图形验证码
            boolean validate = sysCaptchaService.validate(loginVO.getUuid(), loginVO.getCode());
            if (sysUser != null && (PasswordUtils.encode(loginVO.getPassword(), sysUser.getSalt()).equals(sysUser.getPassword()))) {
                String userAgent = request.getHeader("user-agent");
                String remoteIp = request.getRemoteHost();
                String token = MD5Encoder.encode((loginVO.getUsername() + "|" + loginVO.getPassword() + "|" + userAgent + "|" + remoteIp + "|" + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
                request.getSession(true).setAttribute("token", token);
                result.setToken(token);
                return ResultUtils.success(result);
            } else {
                // 用户名或密码错误
                return ResultUtils.error(303, "用户名、密码或验证码不正确");
            }
        } else {
            return ResultUtils.error("用户名、密码、图形验证码不能为空");
        }
    }
}
