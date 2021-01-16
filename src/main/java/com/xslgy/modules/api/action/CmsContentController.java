package com.xslgy.modules.api.action;

import com.xslgy.common.entity.CmsContent;
import com.xslgy.common.service.CmsContentService;
import com.xslgy.common.vo.CmsContentVO;
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
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@Api(tags = "cms内容")
@RestController
@RequestMapping("api/cmscontent")
public class CmsContentController extends BaseController {

    @Resource
    CmsContentService cmsContentService;

    @ApiOperation(value = "查询内容(带分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "分类标识", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "page", value = "当前页", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页记录数", dataType = "Integer", paramType = "query", required = true)
    })
    @GetMapping("listPageByCode")
    public Result listPageByCode(@RequestParam(value = "code", required = false)String code, @RequestParam("page")Integer page, @RequestParam("size")Integer size) {
        return ResultUtils.success(cmsContentService.listPageByCode(code, PageRequest.of(page - 1, size, Sort.by("orderNo"))));
    }
    @ApiOperation(value = "查询内容(不带分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "分类标识", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "page", value = "当前页", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页记录数", dataType = "Integer", paramType = "query", required = true)
    })
    @GetMapping("listByCode")
    public Result listByCode(@RequestParam("code")String code) {
        return ResultUtils.success(cmsContentService.listByCode(code));
    }

    @ApiOperation(value = "新增内容")
    @PostMapping("insert")
    public Result insert(@RequestBody CmsContentVO cmsContentVO) {
        CmsContent cmsContent = new CmsContent();
        BeanUtils.copyProperties(cmsContentVO, cmsContent);
        return ResultUtils.success(cmsContentService.save(cmsContent));
    }
    @ApiOperation(value = "更新内容")
    @PostMapping("update")
    public Result update(@RequestBody CmsContentVO cmsContentVO) {
        if (cmsContentVO.getId() == null) {
            throw new XSLGYException("更新内容id不能为空");
        }
        CmsContent cmsContent = new CmsContent();
        BeanUtils.copyProperties(cmsContentVO, cmsContent);
        return ResultUtils.success(cmsContentService.save(cmsContent));
    }
    @ApiOperation(value = "通过id删除内容")
    @PostMapping("delete/{id}")
    @ApiImplicitParam(name = "id", value = "内容Id", dataType = "Long", paramType = "path", required = true)
    public Result delete(@PathVariable("id")Long id) {
        cmsContentService.deleteById(id);
        return ResultUtils.success();
    }
    @ApiOperation(value = "通过id获取内容")
    @GetMapping("get/{id}")
    @ApiImplicitParam(name = "id", value = "内容Id", dataType = "Long", paramType = "path", required = true)
    public Result get(@PathVariable("id")Long id) {
        return ResultUtils.success(cmsContentService.getById(id));
    }
    @ApiOperation(value = "通过标题模糊查询（带分页）")
    @GetMapping("listPageByTitle")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页记录数", dataType = "Integer", paramType = "query", required = true)
    })
    public Result listPageByTitle(@RequestParam(value = "title", required = false)String title, @RequestParam("page")Integer page, @RequestParam("size")Integer size) {
        return ResultUtils.success(cmsContentService.listPageByTitle(title, PageRequest.of(page - 1, size)));
    }
}
