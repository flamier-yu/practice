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
import com.ruoyi.bbs.domain.BbsImage;
import com.ruoyi.bbs.service.IBbsImageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 论坛图片Controller
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@RestController
@RequestMapping("/bbs/image")
public class BbsImageController extends BaseController
{
    @Autowired
    private IBbsImageService bbsImageService;

    /**
     * 查询论坛图片列表
     */
    @PreAuthorize("@ss.hasPermi('bbs:image:list')")
    @GetMapping("/list")
    public TableDataInfo list(BbsImage bbsImage)
    {
        startPage();
        List<BbsImage> list = bbsImageService.selectBbsImageList(bbsImage);
        return getDataTable(list);
    }

    /**
     * 导出论坛图片列表
     */
    @PreAuthorize("@ss.hasPermi('bbs:image:export')")
    @Log(title = "论坛图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BbsImage bbsImage)
    {
        List<BbsImage> list = bbsImageService.selectBbsImageList(bbsImage);
        ExcelUtil<BbsImage> util = new ExcelUtil<BbsImage>(BbsImage.class);
        util.exportExcel(response, list, "论坛图片数据");
    }

    /**
     * 获取论坛图片详细信息
     */
    @PreAuthorize("@ss.hasPermi('bbs:image:query')")
    @GetMapping(value = "/{imageId}")
    public AjaxResult getInfo(@PathVariable("imageId") Long imageId)
    {
        return success(bbsImageService.selectBbsImageByImageId(imageId));
    }

    /**
     * 新增论坛图片
     */
    @PreAuthorize("@ss.hasPermi('bbs:image:add')")
    @Log(title = "论坛图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BbsImage bbsImage)
    {
        return toAjax(bbsImageService.insertBbsImage(bbsImage));
    }

    /**
     * 修改论坛图片
     */
    @PreAuthorize("@ss.hasPermi('bbs:image:edit')")
    @Log(title = "论坛图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BbsImage bbsImage)
    {
        return toAjax(bbsImageService.updateBbsImage(bbsImage));
    }

    /**
     * 删除论坛图片
     */
    @PreAuthorize("@ss.hasPermi('bbs:image:remove')")
    @Log(title = "论坛图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{imageIds}")
    public AjaxResult remove(@PathVariable Long[] imageIds)
    {
        return toAjax(bbsImageService.deleteBbsImageByImageIds(imageIds));
    }
}
