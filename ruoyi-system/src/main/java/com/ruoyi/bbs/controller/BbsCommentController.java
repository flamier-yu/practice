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
import com.ruoyi.bbs.domain.BbsComment;
import com.ruoyi.bbs.service.IBbsCommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 评论Controller
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@RestController
@RequestMapping("/bbs/comment")
public class BbsCommentController extends BaseController
{
    @Autowired
    private IBbsCommentService bbsCommentService;

    /**
     * 查询评论列表
     */
    @PreAuthorize("@ss.hasPermi('bbs:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(BbsComment bbsComment)
    {
        startPage();
        List<BbsComment> list = bbsCommentService.selectBbsCommentList(bbsComment);
        return getDataTable(list);
    }

    /**
     * 导出评论列表
     */
    @PreAuthorize("@ss.hasPermi('bbs:comment:export')")
    @Log(title = "评论", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BbsComment bbsComment)
    {
        List<BbsComment> list = bbsCommentService.selectBbsCommentList(bbsComment);
        ExcelUtil<BbsComment> util = new ExcelUtil<BbsComment>(BbsComment.class);
        util.exportExcel(response, list, "评论数据");
    }

    /**
     * 获取评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('bbs:comment:query')")
    @GetMapping(value = "/{commentId}")
    public AjaxResult getInfo(@PathVariable("commentId") Long commentId)
    {
        return success(bbsCommentService.selectBbsCommentByCommentId(commentId));
    }

    /**
     * 新增评论
     */
    @PreAuthorize("@ss.hasPermi('bbs:comment:add')")
    @Log(title = "评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BbsComment bbsComment)
    {
        return toAjax(bbsCommentService.insertBbsComment(bbsComment));
    }

    /**
     * 修改评论
     */
    @PreAuthorize("@ss.hasPermi('bbs:comment:edit')")
    @Log(title = "评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BbsComment bbsComment)
    {
        return toAjax(bbsCommentService.updateBbsComment(bbsComment));
    }

    /**
     * 删除评论
     */
    @PreAuthorize("@ss.hasPermi('bbs:comment:remove')")
    @Log(title = "评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{commentIds}")
    public AjaxResult remove(@PathVariable Long[] commentIds)
    {
        return toAjax(bbsCommentService.deleteBbsCommentByCommentIds(commentIds));
    }
}
