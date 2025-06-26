package com.ruoyi.bbs.service;

import java.util.List;
import com.ruoyi.bbs.domain.BbsCategory;

/**
 * 论坛分类Service接口
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
public interface IBbsCategoryService 
{
    /**
     * 查询论坛分类
     * 
     * @param categoryId 论坛分类主键
     * @return 论坛分类
     */
    public BbsCategory selectBbsCategoryByCategoryId(Long categoryId);

    /**
     * 查询论坛分类列表
     * 
     * @param bbsCategory 论坛分类
     * @return 论坛分类集合
     */
    public List<BbsCategory> selectBbsCategoryList(BbsCategory bbsCategory);

    /**
     * 新增论坛分类
     * 
     * @param bbsCategory 论坛分类
     * @return 结果
     */
    public int insertBbsCategory(BbsCategory bbsCategory);

    /**
     * 修改论坛分类
     * 
     * @param bbsCategory 论坛分类
     * @return 结果
     */
    public int updateBbsCategory(BbsCategory bbsCategory);

    /**
     * 批量删除论坛分类
     * 
     * @param categoryIds 需要删除的论坛分类主键集合
     * @return 结果
     */
    public int deleteBbsCategoryByCategoryIds(Long[] categoryIds);

    /**
     * 删除论坛分类信息
     * 
     * @param categoryId 论坛分类主键
     * @return 结果
     */
    public int deleteBbsCategoryByCategoryId(Long categoryId);
}
