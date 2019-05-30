package com.cjr.WechatMessage.service.Impl;

import com.cjr.WechatMessage.dao.NewsPostDao;
import com.cjr.WechatMessage.entity.Post;
import com.cjr.WechatMessage.service.NoticePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("noticePostService")
public class NoticePostServiceImpl implements NoticePostService {

    @Autowired
    @Resource(name = "newsPostDao")
    private NewsPostDao newsPostDao;


    public boolean addNoticePost(Post post) {
        try {
            newsPostDao.insert(post);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void changeLikeNum(String postId, Boolean isClicked) {
        Post post = newsPostDao.selectByPostId(postId);
        int postLikeNum = post.getPostLikeNum();
        if(isClicked == true){
            post.setPostLikeNum(postLikeNum+1);
        }else if(isClicked == false){
            post.setPostLikeNum(postLikeNum-1);
        }
        newsPostDao.update(post);
    }
}
