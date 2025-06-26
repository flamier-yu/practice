package com.ruoyi.bbs.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bbs.mapper.BbsCommentMapper;
import com.ruoyi.bbs.domain.BbsComment;
import com.ruoyi.bbs.service.IBbsCommentService;

/**
 * 评论Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@Service
public class BbsCommentServiceImpl implements IBbsCommentService 
{
    @Autowired
    private BbsCommentMapper bbsCommentMapper;

    /**
     * 查询评论
     * 
     * @param commentId 评论主键
     * @return 评论
     */
    @Override
    public BbsComment selectBbsCommentByCommentId(Long commentId)
    {
        return bbsCommentMapper.selectBbsCommentByCommentId(commentId);
    }

    /**
     * 查询评论列表
     * 
     * @param bbsComment 评论
     * @return 评论
     */
    @Override
    public List<BbsComment> selectBbsCommentList(BbsComment bbsComment)
    {
        return bbsCommentMapper.selectBbsCommentList(bbsComment);
    }

    /**
     * 新增评论
     * 
     * @param bbsComment 评论
     * @return 结果
     */
    @Override
    public int insertBbsComment(BbsComment bbsComment)
    {
        bbsComment.setCreateTime(DateUtils.getNowDate());
        return bbsCommentMapper.insertBbsComment(bbsComment);
    }

    /**
     * 修改评论
     * 
     * @param bbsComment 评论
     * @return 结果
     */
    @Override
    public int updateBbsComment(BbsComment bbsComment)
    {
        return bbsCommentMapper.updateBbsComment(bbsComment);
    }

    /**
     * 批量删除评论
     * 
     * @param commentIds 需要删除的评论主键
     * @return 结果
     */
    @Override
    public int deleteBbsCommentByCommentIds(Long[] commentIds)
    {
        return bbsCommentMapper.deleteBbsCommentByCommentIds(commentIds);
    }

    /**
     * 删除评论信息
     * 
     * @param commentId 评论主键
     * @return 结果
     */
    @Override
    public int deleteBbsCommentByCommentId(Long commentId)
    {
        return bbsCommentMapper.deleteBbsCommentByCommentId(commentId);
    }
}
