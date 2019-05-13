package com.cjr.WechatMessage.service.Impl;

import com.cjr.WechatMessage.dao.BlinddatePostDao;
import com.cjr.WechatMessage.dao.EmploymentPostDao;
import com.cjr.WechatMessage.dao.TransactionPostDao;
import com.cjr.WechatMessage.entity.Common;
import com.cjr.WechatMessage.entity.Post;
import com.cjr.WechatMessage.entity.Utils;
import com.cjr.WechatMessage.service.PostViewNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostViewNumberServiceImpl implements PostViewNumberService {

    @Autowired
    private BlinddatePostDao blinddatePostDao;

    @Autowired
    private EmploymentPostDao employmentPostDao;

    @Autowired
    private TransactionPostDao transactionPostDao;

    public boolean numAdd(String postId,int postType) {

        if(postType == Common.Blinddate.hashCode()){
            blinddatePostDao.updateLookPeopleNum(postId);


        }else if(postType == Common.Employment.hashCode()){
            employmentPostDao.updateLookPeopleNum(postId);


        }else if(postType == Common.Transaction.hashCode()){
            transactionPostDao.updateLookPeopleNum(postId);

        }else{
            return false;
        }


        return true;
    }
}
