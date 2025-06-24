package com.ruoyi.student.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.TestCourseMapper;
import com.ruoyi.student.domain.TestCourse;
import com.ruoyi.student.service.ITestCourseService;

/**
 * 课程Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-24
 */
@Service
public class TestCourseServiceImpl implements ITestCourseService 
{
    @Autowired
    private TestCourseMapper testCourseMapper;

    /**
     * 查询课程
     * 
     * @param courseId 课程主键
     * @return 课程
     */
    @Override
    public TestCourse selectTestCourseByCourseId(Long courseId)
    {
        return testCourseMapper.selectTestCourseByCourseId(courseId);
    }

    /**
     * 查询课程列表
     * 
     * @param testCourse 课程
     * @return 课程
     */
    @Override
    public List<TestCourse> selectTestCourseList(TestCourse testCourse)
    {
        return testCourseMapper.selectTestCourseList(testCourse);
    }

    /**
     * 新增课程
     * 
     * @param testCourse 课程
     * @return 结果
     */
    @Override
    public int insertTestCourse(TestCourse testCourse)
    {
        return testCourseMapper.insertTestCourse(testCourse);
    }

    /**
     * 修改课程
     * 
     * @param testCourse 课程
     * @return 结果
     */
    @Override
    public int updateTestCourse(TestCourse testCourse)
    {
        return testCourseMapper.updateTestCourse(testCourse);
    }

    /**
     * 批量删除课程
     * 
     * @param courseIds 需要删除的课程主键
     * @return 结果
     */
    @Override
    public int deleteTestCourseByCourseIds(Long[] courseIds)
    {
        return testCourseMapper.deleteTestCourseByCourseIds(courseIds);
    }

    /**
     * 删除课程信息
     * 
     * @param courseId 课程主键
     * @return 结果
     */
    @Override
    public int deleteTestCourseByCourseId(Long courseId)
    {
        return testCourseMapper.deleteTestCourseByCourseId(courseId);
    }
}
