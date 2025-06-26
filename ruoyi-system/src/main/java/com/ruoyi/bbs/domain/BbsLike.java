package com.ruoyi.bbs.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 点赞对象 bbs_like
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public class BbsLike extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long likeId;

    /** 被点赞的帖子ID */
    @Excel(name = "被点赞的帖子ID")
    private Long postId;

    /** 被点赞的评论ID */
    @Excel(name = "被点赞的评论ID")
    private Long commentId;

    /** 点赞人ID */
    @Excel(name = "点赞人ID")
    private Long userId;

    public void setLikeId(Long likeId) 
    {
        this.likeId = likeId;
    }

    public Long getLikeId() 
    {
        return likeId;
    }

    public void setPostId(Long postId) 
    {
        this.postId = postId;
    }

    public Long getPostId() 
    {
        return postId;
    }

    public void setCommentId(Long commentId) 
    {
        this.commentId = commentId;
    }

    public Long getCommentId() 
    {
        return commentId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("likeId", getLikeId())
            .append("postId", getPostId())
            .append("commentId", getCommentId())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
