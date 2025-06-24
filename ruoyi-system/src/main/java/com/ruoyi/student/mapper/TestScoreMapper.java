package com.ruoyi.student.mapper;

import java.util.List;
import com.ruoyi.student.domain.TestScore;

/**
 * 分数Mapper接口
 * 
 * @author ruoyi
 * @date 2025-06-24
 */
public interface TestScoreMapper 
{
    /**
     * 查询分数
     * 
     * @param scoreId 分数主键
     * @return 分数
     */
    public TestScore selectTestScoreByScoreId(Long scoreId);

    /**
     * 查询分数列表
     * 
     * @param testScore 分数
     * @return 分数集合
     */
    public List<TestScore> selectTestScoreList(TestScore testScore);

    /**
     * 新增分数
     * 
     * @param testScore 分数
     * @return 结果
     */
    public int insertTestScore(TestScore testScore);

    /**
     * 修改分数
     * 
     * @param testScore 分数
     * @return 结果
     */
    public int updateTestScore(TestScore testScore);

    /**
     * 删除分数
     * 
     * @param scoreId 分数主键
     * @return 结果
     */
    public int deleteTestScoreByScoreId(Long scoreId);

    /**
     * 批量删除分数
     * 
     * @param scoreIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTestScoreByScoreIds(Long[] scoreIds);
}
