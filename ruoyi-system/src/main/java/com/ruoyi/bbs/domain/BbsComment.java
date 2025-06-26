package com.ruoyi.bbs.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 评论对象 bbs_comment
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public class BbsComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long commentId;

    /** 关联帖子ID */
    @Excel(name = "关联帖子ID")
    private Long postId;

    /** 评论人ID */
    @Excel(name = "评论人ID")
    private Long userId;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    /** 父评论ID（0表示顶级评论） */
    @Excel(name = "父评论ID", readConverterExp = "0=表示顶级评论")
    private Long parentId;

    public void setCommentId(Long commentId) 
    {
        this.commentId = commentId;
    }

    public Long getCommentId() 
    {
        return commentId;
    }

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

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("commentId", getCommentId())
            .append("postId", getPostId())
            .append("userId", getUserId())
            .append("content", getContent())
            .append("parentId", getParentId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
