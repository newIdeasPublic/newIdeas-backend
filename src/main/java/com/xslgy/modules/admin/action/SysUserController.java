package com.xslgy.modules.admin.action;

import com.xslgy.core.action.BaseController;
import com.xslgy.core.utils.Result;
import com.xslgy.core.utils.ResultUtils;
import com.xslgy.modules.admin.entity.SysUser;
import com.xslgy.modules.admin.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "用户管理")
@RestController
@RequestMapping("admin/sysuser")
public class SysUserController extends BaseController {

    @Resource
    SysUserService sysUserService;

    @ApiOperation("分页查询用户信息")
    @GetMapping("list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "mobile", value = "手机号", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页记录数", dataType = "Integer", paramType = "query", required = true)
    })
    public Result list(@RequestParam("username")String username, @RequestParam("mobile")String mobile,
                       @RequestParam("page")Integer page, @RequestParam("size")Integer size) {
        return ResultUtils.success(sysUserService.findAllPage(username, mobile, PageRequest.of(page - 1, size)));
    }
    @ApiOperation("通过id获取用户详情")
    @GetMapping("get/{id}")
    @ApiImplicitParam(name = "id", value = "id", dataType = "Long", paramType = "path", required = true)
    public Result getById(@PathVariable("id")Long id) {
        return ResultUtils.success(sysUserService.getById(id));
    }
    @ApiOperation("新增用户")
    @PostMapping("insert")
    public Result insert(@RequestBody SysUser sysUser) {
        if (sysUser.getId() != null) {
            return ResultUtils.error("新增数据，id必须为空");
        } else {
            return ResultUtils.success(sysUserService.save(sysUser));
        }
    }
    @ApiOperation("更新用户")
    @PostMapping("update")
    public Result update(@RequestBody SysUser sysUser) {
        if (sysUser.getId() == null) {
            return ResultUtils.error("更新数据，id不能为空");
        } else {
            return ResultUtils.success(sysUserService.save(sysUser));
        }
    }
    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "id", value = "id", dataType = "Long", paramType = "path", required = true)
    @PostMapping("delete/{id}")
    public Result delete(@PathVariable("id")Long id) {
        if (id == null) {
            return ResultUtils.error("删除数据，id不能为空");
        } else {
            sysUserService.deleteById(id);
            return ResultUtils.success();
        }
    }
}
