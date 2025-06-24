package com.ruoyi.student.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.TestScoreMapper;
import com.ruoyi.student.domain.TestScore;
import com.ruoyi.student.service.ITestScoreService;

/**
 * 分数Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-24
 */
@Service
public class TestScoreServiceImpl implements ITestScoreService 
{
    @Autowired
    private TestScoreMapper testScoreMapper;

    /**
     * 查询分数
     * 
     * @param scoreId 分数主键
     * @return 分数
     */
    @Override
    public TestScore selectTestScoreByScoreId(Long scoreId)
    {
        return testScoreMapper.selectTestScoreByScoreId(scoreId);
    }

    /**
     * 查询分数列表
     * 
     * @param testScore 分数
     * @return 分数
     */
    @Override
    public List<TestScore> selectTestScoreList(TestScore testScore)
    {
        return testScoreMapper.selectTestScoreList(testScore);
    }

    /**
     * 新增分数
     * 
     * @param testScore 分数
     * @return 结果
     */
    @Override
    public int insertTestScore(TestScore testScore)
    {
        return testScoreMapper.insertTestScore(testScore);
    }

    /**
     * 修改分数
     * 
     * @param testScore 分数
     * @return 结果
     */
    @Override
    public int updateTestScore(TestScore testScore)
    {
        return testScoreMapper.updateTestScore(testScore);
    }

    /**
     * 批量删除分数
     * 
     * @param scoreIds 需要删除的分数主键
     * @return 结果
     */
    @Override
    public int deleteTestScoreByScoreIds(Long[] scoreIds)
    {
        return testScoreMapper.deleteTestScoreByScoreIds(scoreIds);
    }

    /**
     * 删除分数信息
     * 
     * @param scoreId 分数主键
     * @return 结果
     */
    @Override
    public int deleteTestScoreByScoreId(Long scoreId)
    {
        return testScoreMapper.deleteTestScoreByScoreId(scoreId);
    }
}
