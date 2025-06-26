package com.ruoyi.bbs.mapper;

import java.util.List;
import com.ruoyi.bbs.domain.BbsImage;

/**
 * 论坛图片Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public interface BbsImageMapper 
{
    /**
     * 查询论坛图片
     * 
     * @param imageId 论坛图片主键
     * @return 论坛图片
     */
    public BbsImage selectBbsImageByImageId(Long imageId);

    /**
     * 查询论坛图片列表
     * 
     * @param bbsImage 论坛图片
     * @return 论坛图片集合
     */
    public List<BbsImage> selectBbsImageList(BbsImage bbsImage);

    /**
     * 新增论坛图片
     * 
     * @param bbsImage 论坛图片
     * @return 结果
     */
    public int insertBbsImage(BbsImage bbsImage);

    /**
     * 修改论坛图片
     * 
     * @param bbsImage 论坛图片
     * @return 结果
     */
    public int updateBbsImage(BbsImage bbsImage);

    /**
     * 删除论坛图片
     * 
     * @param imageId 论坛图片主键
     * @return 结果
     */
    public int deleteBbsImageByImageId(Long imageId);

    /**
     * 批量删除论坛图片
     * 
     * @param imageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBbsImageByImageIds(Long[] imageIds);
}
