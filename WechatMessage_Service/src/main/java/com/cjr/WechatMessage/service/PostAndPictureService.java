package com.cjr.WechatMessage.service;

import com.cjr.WechatMessage.entity.PostAndPicture;

/**
 * @Created with qml
 * @author:qml
 * @Date:2019/5/11
 * @Time:22:39
 */
public interface PostAndPictureService {
    public void insert(PostAndPicture postAndPicture);

    public PostAndPicture selectByPortId(String postId);

    public void deleteByPostId(String postId);
}
