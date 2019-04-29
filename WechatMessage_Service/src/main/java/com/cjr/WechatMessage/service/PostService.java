package com.cjr.WechatMessage.service;

import com.cjr.WechatMessage.entity.Post;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @Created with qml
 * @author:qml
 * @Date:2019/4/27
 * @Time:16:49
 */

public interface PostService {
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
     * 查询用户感兴趣的帖子
     * @param openId
     * @return
     */
    public List<Post> selectLike(String openId, int index);
    /**
     * 查询所有相亲贴
     * @return
     */
    public List<Post> selectAll(int type, int index);
}
