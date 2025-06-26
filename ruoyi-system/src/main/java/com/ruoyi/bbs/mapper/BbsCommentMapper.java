package com.ruoyi.bbs.mapper;

import java.util.List;
import com.ruoyi.bbs.domain.BbsComment;

/**
 * 评论Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public interface BbsCommentMapper 
{
    /**
     * 查询评论
     * 
     * @param commentId 评论主键
     * @return 评论
     */
    public BbsComment selectBbsCommentByCommentId(Long commentId);

    /**
     * 查询评论列表
     * 
     * @param bbsComment 评论
     * @return 评论集合
     */
    public List<BbsComment> selectBbsCommentList(BbsComment bbsComment);

    /**
     * 新增评论
     * 
     * @param bbsComment 评论
     * @return 结果
     */
    public int insertBbsComment(BbsComment bbsComment);

    /**
     * 修改评论
     * 
     * @param bbsComment 评论
     * @return 结果
     */
    public int updateBbsComment(BbsComment bbsComment);

    /**
     * 删除评论
     * 
     * @param commentId 评论主键
     * @return 结果
     */
    public int deleteBbsCommentByCommentId(Long commentId);

    /**
     * 批量删除评论
     * 
     * @param commentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBbsCommentByCommentIds(Long[] commentIds);
}
