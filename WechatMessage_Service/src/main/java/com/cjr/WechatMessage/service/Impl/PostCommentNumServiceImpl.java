package com.cjr.WechatMessage.service.Impl;

import com.cjr.WechatMessage.dao.BlinddatePostDao;
import com.cjr.WechatMessage.dao.EmploymentPostDao;
import com.cjr.WechatMessage.dao.TransactionPostDao;
import com.cjr.WechatMessage.entity.Common;
import com.cjr.WechatMessage.service.PostCommentNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostCommentNumServiceImpl implements PostCommentNumService {

    @Autowired
    private BlinddatePostDao blinddatePostDao;

    @Autowired
    private EmploymentPostDao employmentPostDao;

    @Autowired
    private TransactionPostDao transactionPostDao;
    public boolean addComment(String postId, int postType) {
        if(postType == Common.Blinddate.hashCode()){
            blinddatePostDao.updatePostCommentNum(postId);


        }else if(postType == Common.Employment.hashCode()){
            employmentPostDao.updatePostCommentNum(postId);


        }else if(postType == Common.Transaction.hashCode()){
            transactionPostDao.updatePostCommentNum(postId);

        }else{
            return false;
        }


        return true;
    }
}
