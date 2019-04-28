package com.cjr.WechatMessage.dao;

import com.cjr.WechatMessage.entity.CommentAndUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Created with qml
 * @author:qml
 * @Date:2019/4/27
 * @Time:16:00
 */
@Repository("commentAndUserDao")
public interface CommentAndUserDao {

    /**
     * 插入新的评论
     * @param commentAndUser
     */
    public void insert(CommentAndUser commentAndUser);

    /**
     * 更新评论
     * @param commentAndUser
     */
    public void update(CommentAndUser commentAndUser);

    /**
     * 根据评论id删除评论
     * @param commentid
     */
    public void delete(int commentid);

    /**
     * 根据评论id查询评论
     * @param commentid
     * @return
     */
    public CommentAndUser selectById(int commentid);

    /**
     * 根据帖子id查询评论
     * @param postId
     * @return
     */
    public List<CommentAndUser> selectByPostId(String postId);
}
