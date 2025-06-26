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
import com.ruoyi.bbs.domain.BbsLike;
import com.ruoyi.bbs.service.IBbsLikeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 点赞Controller
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@RestController
@RequestMapping("/bbs/like")
public class BbsLikeController extends BaseController
{
    @Autowired
    private IBbsLikeService bbsLikeService;

    /**
     * 查询点赞列表
     */
    @PreAuthorize("@ss.hasPermi('bbs:like:list')")
    @GetMapping("/list")
    public TableDataInfo list(BbsLike bbsLike)
    {
        startPage();
        List<BbsLike> list = bbsLikeService.selectBbsLikeList(bbsLike);
        return getDataTable(list);
    }

    /**
     * 导出点赞列表
     */
    @PreAuthorize("@ss.hasPermi('bbs:like:export')")
    @Log(title = "点赞", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BbsLike bbsLike)
    {
        List<BbsLike> list = bbsLikeService.selectBbsLikeList(bbsLike);
        ExcelUtil<BbsLike> util = new ExcelUtil<BbsLike>(BbsLike.class);
        util.exportExcel(response, list, "点赞数据");
    }

    /**
     * 获取点赞详细信息
     */
    @PreAuthorize("@ss.hasPermi('bbs:like:query')")
    @GetMapping(value = "/{likeId}")
    public AjaxResult getInfo(@PathVariable("likeId") Long likeId)
    {
        return success(bbsLikeService.selectBbsLikeByLikeId(likeId));
    }

    /**
     * 新增点赞
     */
    @PreAuthorize("@ss.hasPermi('bbs:like:add')")
    @Log(title = "点赞", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BbsLike bbsLike)
    {
        return toAjax(bbsLikeService.insertBbsLike(bbsLike));
    }

    /**
     * 修改点赞
     */
    @PreAuthorize("@ss.hasPermi('bbs:like:edit')")
    @Log(title = "点赞", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BbsLike bbsLike)
    {
        return toAjax(bbsLikeService.updateBbsLike(bbsLike));
    }

    /**
     * 删除点赞
     */
    @PreAuthorize("@ss.hasPermi('bbs:like:remove')")
    @Log(title = "点赞", businessType = BusinessType.DELETE)
	@DeleteMapping("/{likeIds}")
    public AjaxResult remove(@PathVariable Long[] likeIds)
    {
        return toAjax(bbsLikeService.deleteBbsLikeByLikeIds(likeIds));
    }
}
