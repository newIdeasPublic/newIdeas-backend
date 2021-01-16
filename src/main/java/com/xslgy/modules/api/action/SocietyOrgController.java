package com.xslgy.modules.api.action;

import com.xslgy.common.dto.AddSocietyOrgDTO;
import com.xslgy.common.dto.SearchSocietyOrgDTO;
import com.xslgy.common.entity.SocietyOrg;
import com.xslgy.common.service.SocietyOrgService;
import com.xslgy.common.utils.PageUtils;
import com.xslgy.common.vo.SocietyOrgVO;
import com.xslgy.core.utils.Result;
import com.xslgy.core.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Author : 玄机
 * @Data : 2021/1/16  下午8:11
 * @Description : 社会组织相关控制层接口
 */
@Slf4j
@Validated
@RestController
@Api(tags = "社会组织相关接口")
@RequestMapping("/society/org")
public class SocietyOrgController {

    @Resource
    private SocietyOrgService societyOrgService;

    @ApiOperation("添加社会组织")
    @PostMapping("/add")
    public Result<String> addSocietyOrg(@NotNull @Valid @RequestBody AddSocietyOrgDTO addSocietyOrgDTO) {
        log.info("调用【添加社会组织】接口: {}", addSocietyOrgDTO);
        return ResultUtils.success(societyOrgService.addSocietyOrg(addSocietyOrgDTO));
    }

    @ApiOperation("更新社会组织")
    @PostMapping("/update")
    public Result<String> updateSocietyOrg(@RequestBody AddSocietyOrgDTO addSocietyOrgDTO){
        log.info("调用【更新社会组织】接口: {}", addSocietyOrgDTO);
        return ResultUtils.success(societyOrgService.updateSocietyOrg(addSocietyOrgDTO));
    }

    @ApiOperation("分页查询社会组织")
    @GetMapping("/list/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页显示的记录数", defaultValue = "10"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", defaultValue = "1")
    })
    public Result<PageUtils> selectSocietyOrgPage
            (@RequestBody SearchSocietyOrgDTO searchSocietyOrgDTO,
             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        log.info("调用【分页查询社会组织】接口: {}", searchSocietyOrgDTO);
        return ResultUtils.success(societyOrgService.selectSocietyOrgPage(searchSocietyOrgDTO, pageNum, pageSize));
    }

    @ApiOperation("根据ID删除社会组织")
    @DeleteMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", required = true)
    })
    public Result<String> deleteById(@RequestParam("id") Long id){
        log.info("调用【根据ID删除社会组织】接口: {}", id);
        return ResultUtils.success(societyOrgService.deleteById(id));
    }

    @ApiOperation("根据ID查询详情")
    @GetMapping("/get/detail")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", required = true)
    })
    public Result<SocietyOrgVO> getDetailById(@RequestParam("id") Long id){
        log.info("调用【根据ID查询详情】接口: {}", id);
        return ResultUtils.success(societyOrgService.getDetailById(id));
    }
}
