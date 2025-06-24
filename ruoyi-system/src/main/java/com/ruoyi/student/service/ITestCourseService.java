package com.ruoyi.student.service;

import java.util.List;
import com.ruoyi.student.domain.TestCourse;

/**
 * 课程Service接口
 * 
 * @author ruoyi
 * @date 2025-06-24
 */
public interface ITestCourseService 
{
    /**
     * 查询课程
     * 
     * @param courseId 课程主键
     * @return 课程
     */
    public TestCourse selectTestCourseByCourseId(Long courseId);

    /**
     * 查询课程列表
     * 
     * @param testCourse 课程
     * @return 课程集合
     */
    public List<TestCourse> selectTestCourseList(TestCourse testCourse);

    /**
     * 新增课程
     * 
     * @param testCourse 课程
     * @return 结果
     */
    public int insertTestCourse(TestCourse testCourse);

    /**
     * 修改课程
     * 
     * @param testCourse 课程
     * @return 结果
     */
    public int updateTestCourse(TestCourse testCourse);

    /**
     * 批量删除课程
     * 
     * @param courseIds 需要删除的课程主键集合
     * @return 结果
     */
    public int deleteTestCourseByCourseIds(Long[] courseIds);

    /**
     * 删除课程信息
     * 
     * @param courseId 课程主键
     * @return 结果
     */
    public int deleteTestCourseByCourseId(Long courseId);
}
