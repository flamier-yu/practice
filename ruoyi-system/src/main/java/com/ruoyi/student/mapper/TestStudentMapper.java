package com.ruoyi.student.mapper;

import java.util.List;
import com.ruoyi.student.domain.TestStudent;

/**
 * 学生Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-23
 */
public interface TestStudentMapper 
{
    /**
     * 查询学生
     * 
     * @param studentId 学生主键
     * @return 学生
     */
    public TestStudent selectTestStudentByStudentId(Long studentId);

    /**
     * 查询学生列表
     * 
     * @param testStudent 学生
     * @return 学生集合
     */
    public List<TestStudent> selectTestStudentList(TestStudent testStudent);

    /**
     * 新增学生
     * 
     * @param testStudent 学生
     * @return 结果
     */
    public int insertTestStudent(TestStudent testStudent);

    /**
     * 修改学生
     * 
     * @param testStudent 学生
     * @return 结果
     */
    public int updateTestStudent(TestStudent testStudent);

    /**
     * 删除学生
     * 
     * @param studentId 学生主键
     * @return 结果
     */
    public int deleteTestStudentByStudentId(Long studentId);

    /**
     * 批量删除学生
     * 
     * @param studentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTestStudentByStudentIds(Long[] studentIds);
}
