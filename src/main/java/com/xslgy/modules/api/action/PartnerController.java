package com.xslgy.modules.api.action;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xslgy.common.entity.Partner;
import com.xslgy.common.service.PartnerService;
import com.xslgy.common.vo.PartnerVO;
import com.xslgy.core.action.BaseController;
import com.xslgy.core.exception.XSLGYException;
import com.xslgy.core.utils.Result;
import com.xslgy.core.utils.ResultUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    
    @GetMapping("list")
    @ApiOperation("查询所有伙伴（不带分页)")
    public Result list() {
        return ResultUtils.success(partnerService.list());
    }
    
    @ApiOperation("查询所有伙伴（带分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", dataType = "Integer", paramType = "query", readOnly = true),
            @ApiImplicitParam(name = "size", value = "每页记录数", dataType = "Integer", paramType = "query", readOnly =
                    true)})
    @GetMapping("listPage")
    public Result listPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return ResultUtils.success(partnerService.listPage(PageRequest.of(page - 1, size)));
    }
    
    @ApiOperation(value = "更新伙伴")
    @PostMapping("update")
    public Result update(@RequestBody PartnerVO partnerVO) {
        if (partnerVO.getId() == null) {
            throw new XSLGYException("更新伙伴id不能为空");
        }
        Partner partner = new Partner();
        BeanUtils.copyProperties(partnerVO, partner);
        return ResultUtils.success(partnerService.save(partner));
    }
}
