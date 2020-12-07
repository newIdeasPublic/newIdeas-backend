package com.xslgy.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("分页结果")
public class PageUtils implements Serializable {
    @ApiModelProperty("总记录数")
    private int totalCount;
    @ApiModelProperty("每页记录数")
    private int pageSize;
    @ApiModelProperty("总页数")
    private int totalPage;
    @ApiModelProperty("当前页数")
    private int currPage;
    @ApiModelProperty("列表数据")
    private List<?> list;

    public PageUtils(List<?> list, int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int)Math.ceil((double) totalCount / pageSize);
    }

    public PageUtils(Page<?> page) {
        this.list = page.getContent();
        this.totalCount = (int) page.getTotalElements();
        this.pageSize = page.getSize();
        this.currPage = page.getNumber();
        this.totalPage = page.getTotalPages();
    }
}
