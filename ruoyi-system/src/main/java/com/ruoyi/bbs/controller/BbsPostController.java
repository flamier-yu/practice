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
import com.ruoyi.bbs.domain.BbsPost;
import com.ruoyi.bbs.service.IBbsPostService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 论坛主题Controller
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@RestController
@RequestMapping("/bbs/post")
public class BbsPostController extends BaseController
{
    @Autowired
    private IBbsPostService bbsPostService;

    /**
     * 查询论坛主题列表
     */
    @PreAuthorize("@ss.hasPermi('bbs:post:list')")
    @GetMapping("/list")
    public TableDataInfo list(BbsPost bbsPost)
    {
        startPage();
        List<BbsPost> list = bbsPostService.selectBbsPostList(bbsPost);
        return getDataTable(list);
    }

    /**
     * 导出论坛主题列表
     */
    @PreAuthorize("@ss.hasPermi('bbs:post:export')")
    @Log(title = "论坛主题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BbsPost bbsPost)
    {
        List<BbsPost> list = bbsPostService.selectBbsPostList(bbsPost);
        ExcelUtil<BbsPost> util = new ExcelUtil<BbsPost>(BbsPost.class);
        util.exportExcel(response, list, "论坛主题数据");
    }

    /**
     * 获取论坛主题详细信息
     */
    @PreAuthorize("@ss.hasPermi('bbs:post:query')")
    @GetMapping(value = "/{postId}")
    public AjaxResult getInfo(@PathVariable("postId") Long postId)
    {
        return success(bbsPostService.selectBbsPostByPostId(postId));
    }

    /**
     * 新增论坛主题
     */
    @PreAuthorize("@ss.hasPermi('bbs:post:add')")
    @Log(title = "论坛主题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BbsPost bbsPost)
    {
        return toAjax(bbsPostService.insertBbsPost(bbsPost));
    }

    /**
     * 修改论坛主题
     */
    @PreAuthorize("@ss.hasPermi('bbs:post:edit')")
    @Log(title = "论坛主题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BbsPost bbsPost)
    {
        return toAjax(bbsPostService.updateBbsPost(bbsPost));
    }

    /**
     * 删除论坛主题
     */
    @PreAuthorize("@ss.hasPermi('bbs:post:remove')")
    @Log(title = "论坛主题", businessType = BusinessType.DELETE)
	@DeleteMapping("/{postIds}")
    public AjaxResult remove(@PathVariable Long[] postIds)
    {
        return toAjax(bbsPostService.deleteBbsPostByPostIds(postIds));
    }
}
