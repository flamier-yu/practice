package com.ruoyi.bbs.service;

import java.util.List;
import com.ruoyi.bbs.domain.BbsPost;

/**
 * 论坛主题Service接口
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public interface IBbsPostService 
{
    /**
     * 查询论坛主题
     * 
     * @param postId 论坛主题主键
     * @return 论坛主题
     */
    public BbsPost selectBbsPostByPostId(Long postId);

    /**
     * 查询论坛主题列表
     * 
     * @param bbsPost 论坛主题
     * @return 论坛主题集合
     */
    public List<BbsPost> selectBbsPostList(BbsPost bbsPost);

    /**
     * 新增论坛主题
     * 
     * @param bbsPost 论坛主题
     * @return 结果
     */
    public int insertBbsPost(BbsPost bbsPost);

    /**
     * 修改论坛主题
     * 
     * @param bbsPost 论坛主题
     * @return 结果
     */
    public int updateBbsPost(BbsPost bbsPost);

    /**
     * 批量删除论坛主题
     * 
     * @param postIds 需要删除的论坛主题主键集合
     * @return 结果
     */
    public int deleteBbsPostByPostIds(Long[] postIds);

    /**
     * 删除论坛主题信息
     * 
     * @param postId 论坛主题主键
     * @return 结果
     */
    public int deleteBbsPostByPostId(Long postId);
}
