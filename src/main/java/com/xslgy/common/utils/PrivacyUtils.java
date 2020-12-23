package com.xslgy.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.xslgy.common.entity.SysConfig;
import com.xslgy.common.service.SysConfigService;
import com.xslgy.core.exception.XSLGYException;
import com.xslgy.core.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * 隐私信息处理工具类
 */
@Component
public class PrivacyUtils {

    @Resource
    RestTemplate restTemplate;
    @Value("${newIdeas.rsaDecodeUrl}")
    private String rsaDecodeUrl;
    @Resource
    SysConfigService sysConfigService;


    /**
     * 隐私信息加密
     * <pre>
     *     隐私信息，像身份证号、手机号之类的，通过公钥加密后，入库。
     *     如果要查询的话，只能通过完全匹配的方式去查询
     * </pre>
     * @param data  需要加密的数据
     * @return
     */
    public String encode(String data) throws Exception {
        SysConfig sysConfig = sysConfigService.getByCode(Constant.RSA_PUBLIC_KEY_CODE);
        if (sysConfig != null) {
            return Base64Utils.encode(RSAUtils.encryptByPublicKey(data.getBytes(StandardCharsets.UTF_8), sysConfig.getValue()));
        } else {
            throw new XSLGYException("加密公钥为空");
        }
    }

    /**
     * 解密
     * @param data  需要解密的字符串
     * @return
     */
    public String decode(String data) throws JsonProcessingException {
        String result = null;
        // 通过http去调用解密的应用
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("encryptData", data);
        String responseEntity = restTemplate.postForObject(rsaDecodeUrl + "/rsa/decode", params, String.class);
        JsonNode jsonNode = JsonUtil.string2Json(responseEntity);
        if (jsonNode != null && !jsonNode.isEmpty() && jsonNode.get("code").asInt() == 200) {
            // 获取解析结果
            result = jsonNode.get("data").asText();
        }
        return result;
    }

    /**
     * 数据脱敏
     * @param data  需要脱敏的数据
     * @param count 前后需要保留的位数。比如说手机号，该值为3的时候，结果为170*****291，身份证号为：410************218
     * @param content   替换用的字符串。如果传入的是*，则用*替换要脱敏的内容。如果是#，则用#替换要脱敏的内容
     * @return
     */
    public String unSensitive(String data, int count, String content) {
        StringBuffer result = new StringBuffer();
        if (!StringUtils.isEmpty(data) && data.length() > count * 2) {
            result.append(data.substring(0, 3));
            for (int i = 0 ; i < data.length() - count * 2 - 1; i++) {
                result.append(content);
            }
            result.append(data.substring(data.length() - 3, data.length()));
        }
        return result.toString();
    }
}
