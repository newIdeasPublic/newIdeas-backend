package com.xslgy.modules.admin.action;

import com.xslgy.core.action.BaseController;
import com.xslgy.modules.admin.service.SysCaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Api(tags = "登录")
@RestController
@RequestMapping("admin/login")
public class LoginController extends BaseController {

    @Resource
    SysCaptchaService sysCaptchaService;

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
}
