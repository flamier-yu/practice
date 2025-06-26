package com.ruoyi.bbs.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bbs.mapper.BbsPostMapper;
import com.ruoyi.bbs.domain.BbsPost;
import com.ruoyi.bbs.service.IBbsPostService;

/**
 * 论坛主题Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@Service
public class BbsPostServiceImpl implements IBbsPostService 
{
    @Autowired
    private BbsPostMapper bbsPostMapper;

    /**
     * 查询论坛主题
     * 
     * @param postId 论坛主题主键
     * @return 论坛主题
     */
    @Override
    public BbsPost selectBbsPostByPostId(Long postId)
    {
        return bbsPostMapper.selectBbsPostByPostId(postId);
    }

    /**
     * 查询论坛主题列表
     * 
     * @param bbsPost 论坛主题
     * @return 论坛主题
     */
    @Override
    public List<BbsPost> selectBbsPostList(BbsPost bbsPost)
    {
        return bbsPostMapper.selectBbsPostList(bbsPost);
    }

    /**
     * 新增论坛主题
     * 
     * @param bbsPost 论坛主题
     * @return 结果
     */
    @Override
    public int insertBbsPost(BbsPost bbsPost)
    {
        bbsPost.setCreateTime(DateUtils.getNowDate());
        return bbsPostMapper.insertBbsPost(bbsPost);
    }

    /**
     * 修改论坛主题
     * 
     * @param bbsPost 论坛主题
     * @return 结果
     */
    @Override
    public int updateBbsPost(BbsPost bbsPost)
    {
        bbsPost.setUpdateTime(DateUtils.getNowDate());
        return bbsPostMapper.updateBbsPost(bbsPost);
    }

    /**
     * 批量删除论坛主题
     * 
     * @param postIds 需要删除的论坛主题主键
     * @return 结果
     */
    @Override
    public int deleteBbsPostByPostIds(Long[] postIds)
    {
        return bbsPostMapper.deleteBbsPostByPostIds(postIds);
    }

    /**
     * 删除论坛主题信息
     * 
     * @param postId 论坛主题主键
     * @return 结果
     */
    @Override
    public int deleteBbsPostByPostId(Long postId)
    {
        return bbsPostMapper.deleteBbsPostByPostId(postId);
    }
}
