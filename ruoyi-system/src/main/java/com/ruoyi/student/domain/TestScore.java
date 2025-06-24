package com.ruoyi.student.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 分数对象 test_score
 * 
 * @author ruoyi
 * @date 2025-06-24
 */
public class TestScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分数id主键 */
    private Long scoreId;

    /** 学生id */
    @Excel(name = "学生id")
    private Long studentId;

    /** 课程id */
    @Excel(name = "课程id")
    private Long courseId;

    /** 平时成绩 */
    @Excel(name = "平时成绩")
    private Long regularScore;

    /** 最终成绩 */
    @Excel(name = "最终成绩")
    private Long finalScore;

    /** 总成绩 */
    @Excel(name = "总成绩")
    private Long totalScore;

    /** 学期 */
    @Excel(name = "学期")
    private String semester;

    public void setScoreId(Long scoreId) 
    {
        this.scoreId = scoreId;
    }

    public Long getScoreId() 
    {
        return scoreId;
    }

    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }

    public void setRegularScore(Long regularScore) 
    {
        this.regularScore = regularScore;
    }

    public Long getRegularScore() 
    {
        return regularScore;
    }

    public void setFinalScore(Long finalScore) 
    {
        this.finalScore = finalScore;
    }

    public Long getFinalScore() 
    {
        return finalScore;
    }

    public void setTotalScore(Long totalScore) 
    {
        this.totalScore = totalScore;
    }

    public Long getTotalScore() 
    {
        return totalScore;
    }

    public void setSemester(String semester) 
    {
        this.semester = semester;
    }

    public String getSemester() 
    {
        return semester;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scoreId", getScoreId())
            .append("studentId", getStudentId())
            .append("courseId", getCourseId())
            .append("regularScore", getRegularScore())
            .append("finalScore", getFinalScore())
            .append("totalScore", getTotalScore())
            .append("semester", getSemester())
            .toString();
    }
}
