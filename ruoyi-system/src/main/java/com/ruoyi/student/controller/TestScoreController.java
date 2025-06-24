package com.ruoyi.student.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.student.domain.TestScore;
import com.ruoyi.student.service.ITestScoreService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 分数Controller
 * 
 * @author ruoyi
 * @date 2025-06-24
 */
@Api("学生分数管理")
@RestController
@RequestMapping("/student/score")
public class TestScoreController extends BaseController
{
    @Autowired
    private ITestScoreService testScoreService;

    /**
     * 查询分数列表
     */
    @ApiOperation("查询分数列表")
    @PreAuthorize("@ss.hasPermi('student:score:list')")
    @GetMapping("/list")
    public TableDataInfo list(TestScore testScore)
    {
        startPage();
        List<TestScore> list = testScoreService.selectTestScoreList(testScore);
        return getDataTable(list);
    }

    /**
     * 导出分数列表
     */
    @ApiOperation("导出分数列表")
    @PreAuthorize("@ss.hasPermi('student:score:export')")
    @Log(title = "分数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TestScore testScore)
    {
        List<TestScore> list = testScoreService.selectTestScoreList(testScore);
        ExcelUtil<TestScore> util = new ExcelUtil<TestScore>(TestScore.class);
        util.exportExcel(response, list, "分数数据");
    }

    /**
     * 获取分数详细信息
     */
    @ApiOperation("获取分数详细信息")
    @PreAuthorize("@ss.hasPermi('student:score:query')")
    @GetMapping(value = "/{scoreId}")
    public AjaxResult getInfo(@PathVariable("scoreId") Long scoreId)
    {
        return success(testScoreService.selectTestScoreByScoreId(scoreId));
    }

    /**
     * 新增分数
     */
    @ApiOperation("新增分数")
    @PreAuthorize("@ss.hasPermi('student:score:add')")
    @Log(title = "分数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TestScore testScore)
    {
        return toAjax(testScoreService.insertTestScore(testScore));
    }

    /**
     * 修改分数
     */
    @ApiOperation("修改分数")
    @PreAuthorize("@ss.hasPermi('student:score:edit')")
    @Log(title = "分数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TestScore testScore)
    {
        return toAjax(testScoreService.updateTestScore(testScore));
    }

    /**
     * 删除分数
     */
    @ApiOperation("删除分数")
    @PreAuthorize("@ss.hasPermi('student:score:remove')")
    @Log(title = "分数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scoreIds}")
    public AjaxResult remove(@PathVariable Long[] scoreIds)
    {
        return toAjax(testScoreService.deleteTestScoreByScoreIds(scoreIds));
    }
}
