package com.xslgy.modules.api.action;

import com.xslgy.common.entity.CmsCategory;
import com.xslgy.common.service.CmsCategoryService;
import com.xslgy.common.vo.CmsCategoryVO;
import com.xslgy.core.action.BaseController;
import com.xslgy.core.exception.XSLGYException;
import com.xslgy.core.utils.Result;
import com.xslgy.core.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@Api(tags = "cms分类")
@RestController
@RequestMapping("api/cmscategory")
public class CmsCategoryController extends BaseController {

    @Resource
    CmsCategoryService cmsCategoryService;

    @ApiOperation("查询cms分类（带分页）")
    @GetMapping("list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "分类名称", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页记录数", dataType = "Integer", paramType = "query", required = true)
    })
    public Result list(@RequestParam(name = "name", required = false)String name, @RequestParam("page")Integer page, @RequestParam("size")Integer size) {
        return ResultUtils.success(cmsCategoryService.list(name, PageRequest.of(page - 1, size)));
    }
    @ApiOperation("新增cms分类")
    @PostMapping("insert")
    public Result insert(@RequestBody CmsCategoryVO cmsCategoryVO) {
        CmsCategory cmsCategory = new CmsCategory();
        if (cmsCategoryVO.getId() != null) {
            throw new XSLGYException("新增时，id必须为空");
        }
        BeanUtils.copyProperties(cmsCategoryVO, cmsCategory);
        return ResultUtils.success(cmsCategoryService.save(cmsCategory));
    }
    @ApiOperation("更新cms分类")
    @PostMapping("update")
    public Result update(@RequestBody CmsCategoryVO cmsCategoryVO) {
        CmsCategory cmsCategory = new CmsCategory();
        BeanUtils.copyProperties(cmsCategoryVO, cmsCategory);
        return ResultUtils.success(cmsCategoryService.save(cmsCategory));
    }
    @ApiOperation("通过id删除cms分类")
    @PostMapping("delete/{id}")
    @ApiImplicitParam(name = "id", value = "cms分类id", dataType = "Long", paramType = "path", required = true)
    public Result update(@PathVariable("id")Long id) {
        cmsCategoryService.deleteById(id);
        return ResultUtils.success();
    }
    @ApiOperation("通过id获取详情内容")
    @GetMapping("get/{id}")
    @ApiImplicitParam(name = "id", value = "内容id", dataType = "Long", paramType = "path", required = true)
    public Result get(@PathVariable("id")Long id) {
        return ResultUtils.success(cmsCategoryService.getById(id));
    }
    @ApiOperation("通过parentId获取分类列表")
    @GetMapping("getByParentId/{parentId}")
    @ApiImplicitParam(name = "parentId", value = "上一级ID", dataType = "Long", paramType = "path", required = true)
    public Result getByParentId(@PathVariable("parentId")Long parentId) {
        return ResultUtils.success(cmsCategoryService.getByParentId(parentId));
    }
}
