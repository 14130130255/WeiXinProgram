package com.cjr.WechatMessage.service.Impl;

import com.cjr.WechatMessage.dao.EmploymentPostDao;
import com.cjr.WechatMessage.dao.TransactionPostDao;
import com.cjr.WechatMessage.entity.Post;
import com.cjr.WechatMessage.service.EmploymentPostService;
import com.cjr.WechatMessage.service.TransactionPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("employmentPostServiceImpl")
public class EmploymentPostServiceImpl implements EmploymentPostService {
    @Resource(name = "employmentPostDao")
    private EmploymentPostDao employmentPostDao;

    public boolean addEmploymentPost(Post post) {
        try {
            employmentPostDao.insert(post);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void changeLikeNum(String postId, Boolean isClicked) {
        Post post = employmentPostDao.selectByPostId(postId);
        int postLikeNum = post.getPostLikeNum();
        if(isClicked == true){
            post.setPostLikeNum(postLikeNum+1);
        }else if(isClicked == false){
            post.setPostLikeNum(postLikeNum-1);
        }
        employmentPostDao.update(post);
    }
}
