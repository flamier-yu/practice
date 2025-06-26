package com.ruoyi.bbs.mapper;

import java.util.List;
import com.ruoyi.bbs.domain.BbsLike;

/**
 * 点赞Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public interface BbsLikeMapper 
{
    /**
     * 查询点赞
     * 
     * @param likeId 点赞主键
     * @return 点赞
     */
    public BbsLike selectBbsLikeByLikeId(Long likeId);

    /**
     * 查询点赞列表
     * 
     * @param bbsLike 点赞
     * @return 点赞集合
     */
    public List<BbsLike> selectBbsLikeList(BbsLike bbsLike);

    /**
     * 新增点赞
     * 
     * @param bbsLike 点赞
     * @return 结果
     */
    public int insertBbsLike(BbsLike bbsLike);

    /**
     * 修改点赞
     * 
     * @param bbsLike 点赞
     * @return 结果
     */
    public int updateBbsLike(BbsLike bbsLike);

    /**
     * 删除点赞
     * 
     * @param likeId 点赞主键
     * @return 结果
     */
    public int deleteBbsLikeByLikeId(Long likeId);

    /**
     * 批量删除点赞
     * 
     * @param likeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBbsLikeByLikeIds(Long[] likeIds);
}
