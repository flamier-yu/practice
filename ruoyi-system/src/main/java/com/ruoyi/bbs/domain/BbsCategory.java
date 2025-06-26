package com.ruoyi.bbs.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 论坛分类对象 bbs_category
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public class BbsCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long categoryId;

    /** 分类名称（如“技术交流”） */
    @Excel(name = "分类名称", readConverterExp = "如=“技术交流”")
    private String name;

    /** 排序权重（越大越靠前） */
    @Excel(name = "排序权重", readConverterExp = "越=大越靠前")
    private Long orderNum;

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setOrderNum(Long orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Long getOrderNum() 
    {
        return orderNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("categoryId", getCategoryId())
            .append("name", getName())
            .append("orderNum", getOrderNum())
            .append("createTime", getCreateTime())
            .toString();
    }
}
