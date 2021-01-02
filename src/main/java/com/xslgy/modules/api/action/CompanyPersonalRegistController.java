package com.xslgy.modules.api.action;

import com.xslgy.common.entity.CompanyPersonalRegist;
import com.xslgy.common.service.CompanyPersonalRegistService;
import com.xslgy.core.action.BaseController;
import com.xslgy.core.utils.Result;
import com.xslgy.core.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@Api(tags = "个人、个体工商、企业登记表")
@RequestMapping("api/companyPersonalResigt")
public class CompanyPersonalRegistController extends BaseController {

    @Resource
    CompanyPersonalRegistService companyPersonalRegistService;

    @ApiOperation("分页查询")
    @GetMapping("listPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "联系人姓名", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "mobile", value = "联系人电话", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "companyName", value = "公司名称", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "companyCode", value = "公司社会统一信用代码", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "page", value = "当前页码", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页记录数", dataType = "Integer", paramType = "query", required = true)
    })
    public Result listPage(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "mobile", required = false) String mobile,
                           @RequestParam(value = "companyName", required = false) String companyName,
                           @RequestParam(value = "companyCode", required = false) String companyCode,
                           @RequestParam("page") Integer page,
                           @RequestParam("size") Integer size) {
        return ResultUtils.success(companyPersonalRegistService.listPage(name, mobile, companyName, companyCode, PageRequest.of(page - 1, size)));

    }

    @ApiOperation("通过id获取详情")
    @GetMapping("get/{id}")
    @ApiImplicitParam(name = "id", value = "id", dataType = "Long", paramType = "path", required = true)
    public Result getById(@PathVariable("id")Long id) {
        return ResultUtils.success(companyPersonalRegistService.getById(id));
    }

    @ApiOperation("新增")
    @PostMapping("insert")
    public Result insert(@RequestBody CompanyPersonalRegist companyPersonalRegist) {
        if (companyPersonalRegist.getId() != null) {
            return ResultUtils.error("新增数据id必须为空");
        }
        return ResultUtils.success(companyPersonalRegistService.save(companyPersonalRegist));
    }
    @ApiOperation("修改")
    @PostMapping("update")
    public Result update(@RequestBody CompanyPersonalRegist companyPersonalRegist) {
        if (companyPersonalRegist.getId() == null) {
            return ResultUtils.error("修改数据，id不能为空");
        }
        return ResultUtils.success(companyPersonalRegistService.save(companyPersonalRegist));
    }
    @ApiOperation("删除")
    @PostMapping("delete/{id}")
    @ApiImplicitParam(name = "id", value = "id", dataType = "Long", paramType = "path", required = true)
    public Result delete(@PathVariable("id")Long id) {
        companyPersonalRegistService.deleteById(id);
        return ResultUtils.success();
    }
}
