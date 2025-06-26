package com.ruoyi.bbs.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bbs.mapper.BbsLikeMapper;
import com.ruoyi.bbs.domain.BbsLike;
import com.ruoyi.bbs.service.IBbsLikeService;

/**
 * 点赞Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@Service
public class BbsLikeServiceImpl implements IBbsLikeService 
{
    @Autowired
    private BbsLikeMapper bbsLikeMapper;

    /**
     * 查询点赞
     * 
     * @param likeId 点赞主键
     * @return 点赞
     */
    @Override
    public BbsLike selectBbsLikeByLikeId(Long likeId)
    {
        return bbsLikeMapper.selectBbsLikeByLikeId(likeId);
    }

    /**
     * 查询点赞列表
     * 
     * @param bbsLike 点赞
     * @return 点赞
     */
    @Override
    public List<BbsLike> selectBbsLikeList(BbsLike bbsLike)
    {
        return bbsLikeMapper.selectBbsLikeList(bbsLike);
    }

    /**
     * 新增点赞
     * 
     * @param bbsLike 点赞
     * @return 结果
     */
    @Override
    public int insertBbsLike(BbsLike bbsLike)
    {
        bbsLike.setCreateTime(DateUtils.getNowDate());
        return bbsLikeMapper.insertBbsLike(bbsLike);
    }

    /**
     * 修改点赞
     * 
     * @param bbsLike 点赞
     * @return 结果
     */
    @Override
    public int updateBbsLike(BbsLike bbsLike)
    {
        return bbsLikeMapper.updateBbsLike(bbsLike);
    }

    /**
     * 批量删除点赞
     * 
     * @param likeIds 需要删除的点赞主键
     * @return 结果
     */
    @Override
    public int deleteBbsLikeByLikeIds(Long[] likeIds)
    {
        return bbsLikeMapper.deleteBbsLikeByLikeIds(likeIds);
    }

    /**
     * 删除点赞信息
     * 
     * @param likeId 点赞主键
     * @return 结果
     */
    @Override
    public int deleteBbsLikeByLikeId(Long likeId)
    {
        return bbsLikeMapper.deleteBbsLikeByLikeId(likeId);
    }
}
