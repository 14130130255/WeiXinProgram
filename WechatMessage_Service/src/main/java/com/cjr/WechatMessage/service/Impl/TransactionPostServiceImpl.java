package com.cjr.WechatMessage.service.Impl;

import com.cjr.WechatMessage.dao.TransactionPostDao;
import com.cjr.WechatMessage.entity.Post;
import com.cjr.WechatMessage.service.TransactionPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("transactionPostService")
public class TransactionPostServiceImpl implements TransactionPostService {
    @Resource(name = "transactionPostDao")
    private TransactionPostDao transactionPostDao;

    public boolean addTransactionPost(Post post) {
        try {
            transactionPostDao.insert(post);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void changeLikeNum(String postId, Boolean isClicked) {
        Post post = transactionPostDao.selectByPostId(postId);
        int postLikeNum = post.getPostLikeNum();
        if(isClicked == true){
            post.setPostLikeNum(postLikeNum+1);
        }else if(isClicked == false){
            post.setPostLikeNum(postLikeNum-1);
        }
        transactionPostDao.update(post);
    }
}
