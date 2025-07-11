package com.ruoyi.bbs.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.bbs.domain.BbsCategory;
import com.ruoyi.bbs.service.IBbsCategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 论坛分类Controller
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@RestController
@RequestMapping("/bbs/category")
public class BbsCategoryController extends BaseController
{
    @Autowired
    private IBbsCategoryService bbsCategoryService;

    /**
     * 查询论坛分类列表
     */
    @PreAuthorize("@ss.hasPermi('bbs:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(BbsCategory bbsCategory)
    {
        startPage();
        List<BbsCategory> list = bbsCategoryService.selectBbsCategoryList(bbsCategory);
        return getDataTable(list);
    }

    /**
     * 导出论坛分类列表
     */
    @PreAuthorize("@ss.hasPermi('bbs:category:export')")
    @Log(title = "论坛分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BbsCategory bbsCategory)
    {
        List<BbsCategory> list = bbsCategoryService.selectBbsCategoryList(bbsCategory);
        ExcelUtil<BbsCategory> util = new ExcelUtil<BbsCategory>(BbsCategory.class);
        util.exportExcel(response, list, "论坛分类数据");
    }

    /**
     * 获取论坛分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('bbs:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return success(bbsCategoryService.selectBbsCategoryByCategoryId(categoryId));
    }

    /**
     * 新增论坛分类
     */
    @PreAuthorize("@ss.hasPermi('bbs:category:add')")
    @Log(title = "论坛分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BbsCategory bbsCategory)
    {
        return toAjax(bbsCategoryService.insertBbsCategory(bbsCategory));
    }

    /**
     * 修改论坛分类
     */
    @PreAuthorize("@ss.hasPermi('bbs:category:edit')")
    @Log(title = "论坛分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BbsCategory bbsCategory)
    {
        return toAjax(bbsCategoryService.updateBbsCategory(bbsCategory));
    }

    /**
     * 删除论坛分类
     */
    @PreAuthorize("@ss.hasPermi('bbs:category:remove')")
    @Log(title = "论坛分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(bbsCategoryService.deleteBbsCategoryByCategoryIds(categoryIds));
    }
}
