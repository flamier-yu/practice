package com.ruoyi.student.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.TestStudentMapper;
import com.ruoyi.student.domain.TestStudent;
import com.ruoyi.student.service.ITestStudentService;

/**
 * 学生Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-23
 */
@Service
public class TestStudentServiceImpl implements ITestStudentService 
{
    @Autowired
    private TestStudentMapper testStudentMapper;

    /**
     * 查询学生
     * 
     * @param studentId 学生主键
     * @return 学生
     */
    @Override
    public TestStudent selectTestStudentByStudentId(Long studentId)
    {
        return testStudentMapper.selectTestStudentByStudentId(studentId);
    }

    /**
     * 查询学生列表
     * 
     * @param testStudent 学生
     * @return 学生
     */
    @Override
    public List<TestStudent> selectTestStudentList(TestStudent testStudent)
    {
        return testStudentMapper.selectTestStudentList(testStudent);
    }

    /**
     * 新增学生
     * 
     * @param testStudent 学生
     * @return 结果
     */
    @Override
    public int insertTestStudent(TestStudent testStudent)
    {
        return testStudentMapper.insertTestStudent(testStudent);
    }

    /**
     * 修改学生
     * 
     * @param testStudent 学生
     * @return 结果
     */
    @Override
    public int updateTestStudent(TestStudent testStudent)
    {
        return testStudentMapper.updateTestStudent(testStudent);
    }

    /**
     * 批量删除学生
     * 
     * @param studentIds 需要删除的学生主键
     * @return 结果
     */
    @Override
    public int deleteTestStudentByStudentIds(Long[] studentIds)
    {
        return testStudentMapper.deleteTestStudentByStudentIds(studentIds);
    }

    /**
     * 删除学生信息
     * 
     * @param studentId 学生主键
     * @return 结果
     */
    @Override
    public int deleteTestStudentByStudentId(Long studentId)
    {
        return testStudentMapper.deleteTestStudentByStudentId(studentId);
    }
}
