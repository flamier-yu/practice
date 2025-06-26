package com.ruoyi.bbs.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bbs.mapper.BbsImageMapper;
import com.ruoyi.bbs.domain.BbsImage;
import com.ruoyi.bbs.service.IBbsImageService;

/**
 * 论坛图片Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@Service
public class BbsImageServiceImpl implements IBbsImageService 
{
    @Autowired
    private BbsImageMapper bbsImageMapper;

    /**
     * 查询论坛图片
     * 
     * @param imageId 论坛图片主键
     * @return 论坛图片
     */
    @Override
    public BbsImage selectBbsImageByImageId(Long imageId)
    {
        return bbsImageMapper.selectBbsImageByImageId(imageId);
    }

    /**
     * 查询论坛图片列表
     * 
     * @param bbsImage 论坛图片
     * @return 论坛图片
     */
    @Override
    public List<BbsImage> selectBbsImageList(BbsImage bbsImage)
    {
        return bbsImageMapper.selectBbsImageList(bbsImage);
    }

    /**
     * 新增论坛图片
     * 
     * @param bbsImage 论坛图片
     * @return 结果
     */
    @Override
    public int insertBbsImage(BbsImage bbsImage)
    {
        bbsImage.setCreateTime(DateUtils.getNowDate());
        return bbsImageMapper.insertBbsImage(bbsImage);
    }

    /**
     * 修改论坛图片
     * 
     * @param bbsImage 论坛图片
     * @return 结果
     */
    @Override
    public int updateBbsImage(BbsImage bbsImage)
    {
        return bbsImageMapper.updateBbsImage(bbsImage);
    }

    /**
     * 批量删除论坛图片
     * 
     * @param imageIds 需要删除的论坛图片主键
     * @return 结果
     */
    @Override
    public int deleteBbsImageByImageIds(Long[] imageIds)
    {
        return bbsImageMapper.deleteBbsImageByImageIds(imageIds);
    }

    /**
     * 删除论坛图片信息
     * 
     * @param imageId 论坛图片主键
     * @return 结果
     */
    @Override
    public int deleteBbsImageByImageId(Long imageId)
    {
        return bbsImageMapper.deleteBbsImageByImageId(imageId);
    }
}
