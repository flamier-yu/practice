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
import com.ruoyi.student.domain.TestCourse;
import com.ruoyi.student.service.ITestCourseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课程Controller
 * 
 * @author ruoyi
 * @date 2025-06-24
 */
@Api("学生课程管理")
@RestController
@RequestMapping("/student/course")
public class TestCourseController extends BaseController
{
    @Autowired
    private ITestCourseService testCourseService;

    /**
     * 查询课程列表
     */
    @ApiOperation("查询课程列表")
    @PreAuthorize("@ss.hasPermi('student:course:list')")
    @GetMapping("/list")
    public TableDataInfo list(TestCourse testCourse)
    {
        startPage();
        List<TestCourse> list = testCourseService.selectTestCourseList(testCourse);
        return getDataTable(list);
    }

    /**
     * 导出课程列表
     */
    @ApiOperation("导出课程列表")
    @PreAuthorize("@ss.hasPermi('student:course:export')")
    @Log(title = "课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TestCourse testCourse)
    {
        List<TestCourse> list = testCourseService.selectTestCourseList(testCourse);
        ExcelUtil<TestCourse> util = new ExcelUtil<TestCourse>(TestCourse.class);
        util.exportExcel(response, list, "课程数据");
    }

    /**
     * 获取课程详细信息
     */
    @ApiOperation("获取课程详细信息")
    @PreAuthorize("@ss.hasPermi('student:course:query')")
    @GetMapping(value = "/{courseId}")
    public AjaxResult getInfo(@PathVariable("courseId") Long courseId)
    {
        return success(testCourseService.selectTestCourseByCourseId(courseId));
    }

    /**
     * 新增课程
     */
    @ApiOperation("新增课程")
    @PreAuthorize("@ss.hasPermi('student:course:add')")
    @Log(title = "课程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TestCourse testCourse)
    {
        return toAjax(testCourseService.insertTestCourse(testCourse));
    }

    /**
     * 修改课程
     */
    @ApiOperation("修改课程")
    @PreAuthorize("@ss.hasPermi('student:course:edit')")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TestCourse testCourse)
    {
        return toAjax(testCourseService.updateTestCourse(testCourse));
    }

    /**
     * 删除课程
     */
    @ApiOperation("删除课程")
    @PreAuthorize("@ss.hasPermi('student:course:remove')")
    @Log(title = "课程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{courseIds}")
    public AjaxResult remove(@PathVariable Long[] courseIds)
    {
        return toAjax(testCourseService.deleteTestCourseByCourseIds(courseIds));
    }
}
