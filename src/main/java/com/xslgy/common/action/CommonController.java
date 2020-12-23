package com.xslgy.common.action;

import com.xslgy.common.utils.ParamConfig;
import com.xslgy.common.utils.PrivacyUtils;
import com.xslgy.core.action.BaseController;
import com.xslgy.core.exception.XSLGYException;
import com.xslgy.core.utils.Result;
import com.xslgy.core.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

@Api(tags = "公共接口")
@RestController
@RequestMapping("/api/common")
public class CommonController extends BaseController {

    @Resource
    ParamConfig paramConfig;
    @Resource
    PrivacyUtils privacyUtils;

    private final static String[] extend = {"jpg", "jpeg", "png", "gif", "bmp", "mp4", "3gp", "flv", "avi", "rmvb"};


    @ApiOperation("文件上传")
    @ApiImplicitParam(name = "file", value = "文件", dataType = "MultipartFile", paramType = "query", required = true)
    @PostMapping("upload")
    public Result upload(@RequestParam(value = "file", required = true)MultipartFile file) {
        String fileUrl = null;
        if (file != null) {
            try {
                // 获取上传文件名称，
                String fileName = file.getOriginalFilename();
                if (Arrays.asList(extend).contains(fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase())) {
                    Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH) + 1;
                    String pathFilterName = "/ufs";
                    // 上传文件路径
                    String filePath = pathFilterName + "/" + year + "/" + month;
                    File pathFile = new File(paramConfig.getFilePath() + filePath);
                    if (!pathFile.exists()) {
                        pathFile.mkdirs();
                    }
                    String oldFileName = file.getOriginalFilename();
                    String fileExtend = oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length());
                    String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + fileExtend;
                    file.transferTo(new File(paramConfig.getFilePath() + filePath + "/" + newFileName));
                    fileUrl = pathFilterName + "/" + year + "/" + month + "/" + newFileName;
                } else {
                     return ResultUtils.error("不允许的文件格式");
                }
            } catch (Exception e) {
                throw new XSLGYException(e.getMessage(), e);
            }
        }
        return ResultUtils.success(fileUrl);
    }

    @GetMapping("testRsa")
    public Result testRsa() {
        String data = "This is a test String";
        Map<String, String> result = new HashMap<>();
        result.put("originStr", data);
        try {
            String encodeStr = privacyUtils.encode(data);
            result.put("encodeStr", encodeStr);
            String decodeStr = privacyUtils.decode(encodeStr);
            result.put("decodeStr", decodeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtils.success(result);
    }
}
