package com.ruoyi.bbs.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 论坛图片对象 bbs_image
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public class BbsImage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long imageId;

    /** 关联帖子ID（允许NULL） */
    @Excel(name = "关联帖子ID", readConverterExp = "允=许NULL")
    private Long postId;

    /** 关联评论ID（允许NULL） */
    @Excel(name = "关联评论ID", readConverterExp = "允=许NULL")
    private Long commentId;

    /** 文件路径（或云存储URL） */
    @Excel(name = "文件路径", readConverterExp = "或=云存储URL")
    private String filePath;

    /** 文件类型（如image/jpeg） */
    @Excel(name = "文件类型", readConverterExp = "如=image/jpeg")
    private String fileType;

    /** 文件大小（字节） */
    @Excel(name = "文件大小", readConverterExp = "字=节")
    private Long fileSize;

    /** 上传者ID */
    @Excel(name = "上传者ID")
    private Long userId;

    public void setImageId(Long imageId) 
    {
        this.imageId = imageId;
    }

    public Long getImageId() 
    {
        return imageId;
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

    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }

    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public String getFileType() 
    {
        return fileType;
    }

    public void setFileSize(Long fileSize) 
    {
        this.fileSize = fileSize;
    }

    public Long getFileSize() 
    {
        return fileSize;
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
            .append("imageId", getImageId())
            .append("postId", getPostId())
            .append("commentId", getCommentId())
            .append("filePath", getFilePath())
            .append("fileType", getFileType())
            .append("fileSize", getFileSize())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
