package com.cjr.WechatMessage.service.Impl;

import com.cjr.WechatMessage.dao.BlinddatePostDao;
import com.cjr.WechatMessage.entity.Post;
import com.cjr.WechatMessage.service.BlinddatePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("blinddatePostService")
public class BlinddatePostServiceImpl implements BlinddatePostService {

    @Autowired
    @Resource(name = "blinddatePostDao")
    private BlinddatePostDao blinddatePostDao;

    public boolean addBlinddatePost(Post post) {
        try {
            blinddatePostDao.insert(post);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


    }

    public void changeLikeNum(String postId, Boolean isClicked) {
        Post post = blinddatePostDao.selectByPostId(postId);
        int postLikeNum = post.getPostLikeNum();
        if(isClicked == true){
            post.setPostLikeNum(postLikeNum+1);
        }else if(isClicked == false){
            post.setPostLikeNum(postLikeNum-1);
        }
        blinddatePostDao.update(post);
    }
}
