package com.ruoyi.bbs.domain;

import com.ruoyi.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 论坛主题对象 bbs_post
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public class BbsPost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long postId;

    /** 发帖人ID（关联 sys_user.user_id） */
    @Excel(name = "发帖人ID", readConverterExp = "关=联,s=ys_user.user_id")
    private Long userId;

    /** 发帖人全部信息 */
    @Excel(name = "发帖人全部信息")
    private SysUser user;

    /** 帖子标题 */
    @Excel(name = "帖子标题")
    private String title;

    /** 帖子内容（支持富文本HTML） */
    @Excel(name = "帖子内容", readConverterExp = "支=持富文本HTML")
    private String content;

    /** 分类ID（关联 bbs_category.category_id） */
    @Excel(name = "分类ID", readConverterExp = "关=联,b=bs_category.category_id")
    private Long categoryId;

    /** 浏览量 */
    @Excel(name = "浏览量")
    private Long viewCount;

    /** 封面图路径（可选） */
    @Excel(name = "封面图路径", readConverterExp = "可=选")
    private String coverImage;

    public void setPostId(Long postId) 
    {
        this.postId = postId;
    }

    public Long getPostId() 
    {
        return postId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }

    public void setViewCount(Long viewCount) 
    {
        this.viewCount = viewCount;
    }

    public Long getViewCount() 
    {
        return viewCount;
    }

    public void setCoverImage(String coverImage) 
    {
        this.coverImage = coverImage;
    }

    public String getCoverImage() 
    {
        return coverImage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("postId", getPostId())
            .append("userId", getUserId())
            .append("user", getUser())
            .append("title", getTitle())
            .append("content", getContent())
            .append("categoryId", getCategoryId())
            .append("viewCount", getViewCount())
            .append("coverImage", getCoverImage())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
