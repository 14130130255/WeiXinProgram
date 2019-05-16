package com.cjr.WechatMessage.service.Impl;

import com.cjr.WechatMessage.dao.PostAndPictureDao;
import com.cjr.WechatMessage.entity.PostAndPicture;
import com.cjr.WechatMessage.service.PostAndPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Created with qml
 * @author:qml
 * @Date:2019/5/11
 * @Time:22:43
 */
@Service("postAndPictureService")
public class PostAndPictureServiceImpl implements PostAndPictureService{
    @Autowired
    private PostAndPictureDao postAndPictureDao;

    public void insert(PostAndPicture postAndPicture) {
        postAndPictureDao.insert(postAndPicture);
    }

    public PostAndPicture selectByPortId(String postId) {
        return postAndPictureDao.select(postId);
    }

    public void deleteByPostId(String postId) {
        postAndPictureDao.delete(postId);
    }
}
