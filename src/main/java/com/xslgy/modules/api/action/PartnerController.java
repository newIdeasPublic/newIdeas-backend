package com.xslgy.modules.api.action;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xslgy.common.entity.Partner;
import com.xslgy.common.service.PartnerService;
import com.xslgy.common.vo.PartnerVO;
import com.xslgy.core.action.BaseController;
import com.xslgy.core.exception.XSLGYException;
import com.xslgy.core.utils.Result;
import com.xslgy.core.utils.ResultUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lamdaer
 * @createTime 2020/12/15
 */
@Slf4j
@Api(tags = "伙伴管理")
@RestController
@RequestMapping("api/partner")
public class PartnerController extends BaseController {
    @Resource
    PartnerService partnerService;
    
    @PostMapping("insert")
    @ApiOperation("新增伙伴")
    public Result insert(@RequestBody PartnerVO partnerVO) {
        Partner partner = new Partner();
        if (partnerVO.getId() != null) {
            throw new XSLGYException("新增时，id必须为空");
        }
        BeanUtils.copyProperties(partnerVO, partner);
        return ResultUtils.success(partnerService.save(partner));
    }
    
}
