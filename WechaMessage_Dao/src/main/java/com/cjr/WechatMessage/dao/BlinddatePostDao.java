package com.cjr.WechatMessage.dao;

import com.cjr.WechatMessage.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Created with qml
 * @author:qml
 * @Date:2019/4/27
 * @Time:14:17
 */
@Repository()
public interface BlinddatePostDao {

    /**
     * 插入相亲贴
     * @param blinddatePost
     */
    public void insert(Post blinddatePost);

    /**
     * 更新相亲贴
     * @param blinddatePost
     */
    public void update(Post blinddatePost);

    /**
     * 更新帖子喜欢人数
     * @param postId
     */
    public void updatePostLikeNum(String postId);

    /**
     * 更新帖子评论数量
     * @param postId
     */
    public void updatePostCommentNum(String postId);

    /**
     * 更新帖子查看人数
     * @param postId
     */
    public void updateLookPeopleNum(String postId);

    /**
     * 更新帖子是否匿名
     * @param postId
     */
    public void updateIsAnonymous(String postId, int isAnonymous);

    /**
     * 根据帖子id删除帖子
     * @param postId
     */
    public void delete(String postId);

    /**
     * 根据帖子id查找帖子
     * @param postId
     * @return
     */
    public Post selectByPostId(String postId);

    /**
     * 根据用户id查询其发布的所有相亲帖子
     * @param userId
     * @return
     */
    public List<Post> selectByUserId(String userId);

    /**
     * 查询所有相亲贴
     * @return
     */
    public List<Post> selectAll();
}
