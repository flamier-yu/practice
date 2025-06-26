package com.ruoyi.bbs.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bbs.mapper.BbsCategoryMapper;
import com.ruoyi.bbs.domain.BbsCategory;
import com.ruoyi.bbs.service.IBbsCategoryService;

/**
 * 论坛分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@Service
public class BbsCategoryServiceImpl implements IBbsCategoryService 
{
    @Autowired
    private BbsCategoryMapper bbsCategoryMapper;

    /**
     * 查询论坛分类
     * 
     * @param categoryId 论坛分类主键
     * @return 论坛分类
     */
    @Override
    public BbsCategory selectBbsCategoryByCategoryId(Long categoryId)
    {
        return bbsCategoryMapper.selectBbsCategoryByCategoryId(categoryId);
    }

    /**
     * 查询论坛分类列表
     * 
     * @param bbsCategory 论坛分类
     * @return 论坛分类
     */
    @Override
    public List<BbsCategory> selectBbsCategoryList(BbsCategory bbsCategory)
    {
        return bbsCategoryMapper.selectBbsCategoryList(bbsCategory);
    }

    /**
     * 新增论坛分类
     * 
     * @param bbsCategory 论坛分类
     * @return 结果
     */
    @Override
    public int insertBbsCategory(BbsCategory bbsCategory)
    {
        bbsCategory.setCreateTime(DateUtils.getNowDate());
        return bbsCategoryMapper.insertBbsCategory(bbsCategory);
    }

    /**
     * 修改论坛分类
     * 
     * @param bbsCategory 论坛分类
     * @return 结果
     */
    @Override
    public int updateBbsCategory(BbsCategory bbsCategory)
    {
        return bbsCategoryMapper.updateBbsCategory(bbsCategory);
    }

    /**
     * 批量删除论坛分类
     * 
     * @param categoryIds 需要删除的论坛分类主键
     * @return 结果
     */
    @Override
    public int deleteBbsCategoryByCategoryIds(Long[] categoryIds)
    {
        return bbsCategoryMapper.deleteBbsCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除论坛分类信息
     * 
     * @param categoryId 论坛分类主键
     * @return 结果
     */
    @Override
    public int deleteBbsCategoryByCategoryId(Long categoryId)
    {
        return bbsCategoryMapper.deleteBbsCategoryByCategoryId(categoryId);
    }
}
