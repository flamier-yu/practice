package com.ruoyi.bbs.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bbs.mapper.BbsPostMapper;
import com.ruoyi.bbs.domain.BbsPost;
import com.ruoyi.bbs.service.IBbsPostService;

/**
 * 论坛主题Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-06-25
 */
@Service
public class BbsPostServiceImpl implements IBbsPostService 
{
    private static final Logger log = LoggerFactory.getLogger(BbsPostServiceImpl.class);
    @Autowired
    private BbsPostMapper bbsPostMapper;
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询论坛主题
     * 
     * @param postId 论坛主题主键
     * @return 论坛主题
     */
    @Override
    public BbsPost selectBbsPostByPostId(Long postId)
    {
        return bbsPostMapper.selectBbsPostByPostId(postId);
    }

    /**
     * 查询论坛主题列表
     * 
     * @param bbsPost 论坛主题
     * @return 论坛主题
     */
    @Override
    public List<BbsPost> selectBbsPostList(BbsPost bbsPost)
    {
        List<BbsPost> BbsPosts = bbsPostMapper.selectBbsPostList(bbsPost);
        //处理并将需要查询的数据插入进去,此处为用户信息
        List<BbsPost> resultList = BbsPosts.stream().map(item -> {
            BbsPost voBbsPosts = new BbsPost();
            // 复制评论基础字段
            BeanUtils.copyProperties(item, voBbsPosts);
            // 根据 userid 查询用户信息
            SysUser user = sysUserService.selectUserById(item.getUserId());
            voBbsPosts.setUser(user); // 将用户对象注入VO
            return voBbsPosts;
        }).collect(Collectors.toList());
        return resultList;
    }

    /**
     * 新增论坛主题
     * 
     * @param bbsPost 论坛主题
     * @return 结果
     */
    @Override
    public int insertBbsPost(BbsPost bbsPost)
    {
        bbsPost.setCreateTime(DateUtils.getNowDate());
        return bbsPostMapper.insertBbsPost(bbsPost);
    }

    /**
     * 修改论坛主题
     * 
     * @param bbsPost 论坛主题
     * @return 结果
     */
    @Override
    public int updateBbsPost(BbsPost bbsPost)
    {
        bbsPost.setUpdateTime(DateUtils.getNowDate());
        return bbsPostMapper.updateBbsPost(bbsPost);
    }

    /**
     * 批量删除论坛主题
     * 
     * @param postIds 需要删除的论坛主题主键
     * @return 结果
     */
    @Override
    public int deleteBbsPostByPostIds(Long[] postIds)
    {
        return bbsPostMapper.deleteBbsPostByPostIds(postIds);
    }

    /**
     * 删除论坛主题信息
     * 
     * @param postId 论坛主题主键
     * @return 结果
     */
    @Override
    public int deleteBbsPostByPostId(Long postId)
    {
        return bbsPostMapper.deleteBbsPostByPostId(postId);
    }
}
