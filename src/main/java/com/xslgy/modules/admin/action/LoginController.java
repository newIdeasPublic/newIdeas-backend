package com.xslgy.modules.admin.action;

import com.xslgy.core.action.BaseController;
import com.xslgy.modules.admin.service.SysCaptchaService;
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

@RestController
@RequestMapping("admin/login")
public class LoginController extends BaseController {

    @Resource
    SysCaptchaService sysCaptchaService;

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
