package com.xslgy.modules.api.action;

import com.xslgy.common.entity.PublicResource;
import com.xslgy.common.service.PublicResourceService;
import com.xslgy.core.action.BaseController;
import com.xslgy.core.utils.Result;
import com.xslgy.core.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@Api(tags = "公共资源管理")
public class PublicResourceController extends BaseController {

    @Resource
    PublicResourceService publicResourceService;

    @ApiOperation("分页查询")
    @GetMapping("listPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "资源名称", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "levelOne", value = "一级分类", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "levelTwo", value = "二级分类", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "mobile", value = "联系方式", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "page", value = "当前页码", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页记录数", dataType = "Integer", paramType = "query", required = true)
    })
    public Result listPage(@RequestParam(value = "name", required = false)String name, @RequestParam(value = "levelOne", required = false)String levelOne,
                           @RequestParam(value = "levelTwo", required = false)String levelTwo, @RequestParam(value = "mobile", required = false)String mobile,
                           @RequestParam("page")Integer page, @RequestParam("size")Integer size) {
        return ResultUtils.success(publicResourceService.listPage(name, levelOne, levelTwo, mobile, PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id"))));
    }
    @ApiOperation("通过id获取详情")
    @GetMapping("get/{id}")
    @ApiImplicitParam(name = "id", value = "id", dataType = "Long", paramType = "path", required = true)
    public Result getById(@PathVariable("id")Long id) {
        return ResultUtils.success(publicResourceService.getById(id));
    }

    @ApiOperation("新增")
    @PostMapping("insert")
    public Result insert(@RequestBody PublicResource publicResource) {
        if (publicResource.getId() != null) {
            return ResultUtils.error("新增数据id必须为空");
        }
        return ResultUtils.success(publicResourceService.save(publicResource));
    }
    @ApiOperation("修改")
    @PostMapping("update")
    public Result update(@RequestBody PublicResource publicResource) {
        if (publicResource.getId() == null) {
            return ResultUtils.error("修改数据，id不能为空");
        }
        return ResultUtils.success(publicResourceService.save(publicResource));
    }
    @ApiOperation("删除")
    @PostMapping("delete/{id}")
    @ApiImplicitParam(name = "id", value = "id", dataType = "Long", paramType = "path", required = true)
    public Result delete(@PathVariable("id")Long id) {
        publicResourceService.deleteById(id);
        return ResultUtils.success();
    }
}
