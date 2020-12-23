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
            String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiTXsxk/kKSyV3vNZautsGvJItXlprM6BVY6enA5gDCyXir0gvxwyYzclqRB1wwl9mY3rJif+RL6T0iA9W1H0dwIwXVZKf8eHtQ+Nuk+gjA6/Al54yGNlPDBjxlaPDkRBpJm3X1OOLLi2fwvU67Z57SGbH+XXTobjNjcr0t/y+cc9VXolLASAFVG2XZ+tmXIU2mFzbnL01FDq2jreOfavTnE9xSu7k8tJnI77KMBKGVB5Bj5PZKtLEnB/8ziHT5SW8XlpJleg5z9wO3met0z3NQAG0vszUOPcudE3hMW9kWIlGO5uYxb6cFlJi+x+UPCYTxVs3TDfI0cJSNXJUkJgeQIDAQAB";
            String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCJNezGT+QpLJXe81lq62wa8ki1eWmszoFVjp6cDmAMLJeKvSC/HDJjNyWpEHXDCX2ZjesmJ/5EvpPSID1bUfR3AjBdVkp/x4e1D426T6CMDr8CXnjIY2U8MGPGVo8OREGkmbdfU44suLZ/C9TrtnntIZsf5ddOhuM2NyvS3/L5xz1VeiUsBIAVUbZdn62ZchTaYXNucvTUUOraOt459q9OcT3FK7uTy0mcjvsowEoZUHkGPk9kq0sScH/zOIdPlJbxeWkmV6DnP3A7eZ63TPc1AAbS+zNQ49y50TeExb2RYiUY7m5jFvpwWUmL7H5Q8JhPFWzdMN8jRwlI1clSQmB5AgMBAAECggEAC1j2WABLckxJqMKfZoRJ0ICxGrEL2ErLz3OYEnft4ZPehI7XonaGhEYPS0gdNSAXzxc5l1I/aRgJyZr4+BBzE1SPA2kbiC3MP2El636iXHLrwRpnCsuNl8i4t7J46kki1KYR0ERswa4VCbTEyymwM7hPx/eOMuRc7iLp8OqsgZOlewPY7ccDjmXo0v8mRr9RfXkJvX8kzQ7aVw43XzhQBVl4Ue2TNNCAf9F6JK6U6iZc5IqiBP73UB5kZjym3kOENqkIdbEHaFzXfbl9mUkFmVJ04vgXz1oryYeZGMOxx7E+qNvbTvqrLTyrx+5kclgYIcKnw/wVECXATtpTb4z5wQKBgQDTA++4S6CtN/4HZnbFDngzJgk+zmU94oAi9Qi1slEqFfAvR6WT8sb9fF9WdYPCt80IynmOVGlDula6VH1eL0ExIbR1EIBsRtplpa0dYTBBc2UoJ5fJL4I0MEAy7s2UWlQiGRr9xtwR2SuJzQwLIn2W9EowLEBeFcIKTkyRpcGWDQKBgQCmdiFiB+EFblsWpSpcFBBaZK+HQbxdwplz65fgetBxflZ38Q9Dqqp+Xa8UERmwuNVKT3CPNBupTN+OnJpsb8CrzUEfOLjsxHfw9VnbUyeIHQteITv8Y2nSrtCQMBlK36BSQCHWf5u6TqMGwTrt83pBsTEv6H32jmz6+4uQwpalHQKBgQC1nmK6deVSkanEBs3c54pPQ8KbWxvKeuOcstgPQu4W0hELMJhz0XayAFUyXizM8FO56ENdXIvJbl1tPIE3CmcK+Z6PT2epSgZ5jHKGofrdqot7VzCOSaTAdHMdI2qv8FsPihowKPOLLScLEP/3qXT88z/TXJL+J63+HsxKY6dOFQKBgDKv/fJP2+E/fVUj7gYjxecAicJx7d3PB9MYhiLAwwAMLGBRcwhzy363IDzICD6bybbbAbdZcqLW2awfW5ZxYQXvdkj/iK/wE2W4ECiM5Zi432FGVXHBA9Q3jyKYwtrdxX/GL4eVS+IrV9ESvdL/NNcUEZo4/UI6lEzIk/L8jcqxAoGBAKAt7LAS2SvwSu7EL0FcmCN6WBVTuuHvPZ8G/4oMTYCtR3KZMz07HdsUfuQhxGYz2ESUsx4r8iJIEGlmnN14mahI/9sfzLVl29Hj9wUm886EJWtFh4KveVcMGcB8JFEZB2TPCYbpulFvuQeIkaLTFMStybf5j3sWZhV/czHNbiJA";
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
