package com.cjr.WechatMessage.service.Impl;

import com.cjr.WechatMessage.dao.BlinddatePostDao;
import com.cjr.WechatMessage.dao.CommentAndUserDao;
import com.cjr.WechatMessage.dao.EmploymentPostDao;
import com.cjr.WechatMessage.dao.TransactionPostDao;
import com.cjr.WechatMessage.entity.*;
import com.cjr.WechatMessage.service.PostDetailService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
*
* 返回帖子细节，以及评论
* {“post”:{...},"commentAndUser":{...}}
*
* */
@Service("postDetailService")
public class PostDetailServiceImpl implements PostDetailService {

    @Autowired
    private BlinddatePostDao blinddatePostDao;

    @Autowired
    private EmploymentPostDao employmentPostDao;

    @Autowired
    private TransactionPostDao transactionPostDao;

    @Autowired
    private CommentAndUserDao commentAndUserDao;
    public String getPostDetail(String postId,int postType) {

        String postJsonString;
        Post post;

        if(postType == Common.Blinddate.hashCode()){
            post = blinddatePostDao.selectByPostId(postId);
            postJsonString = Utils.objectToJson(post);

        }else if(postType == Common.Employment.hashCode()){
            post = employmentPostDao.selectByPostId(postId);
            postJsonString = Utils.objectToJson(post);

        }else if(postType == Common.Transaction.hashCode()){
            post = transactionPostDao.selectByPostId(postId);
            postJsonString = Utils.objectToJson(post);

        }else
            postJsonString = "type error";
        String commentAndUserJsonString;
        List<CommentAndUser> commentAndUserList = commentAndUserDao.selectByPostId(postId);
        commentAndUserJsonString = Utils.objectToJson(commentAndUserList);

        JSONObject returnobj = new JSONObject();
        returnobj.put("post",postJsonString);
        returnobj.put("commentAndUser",commentAndUserJsonString);

        return returnobj.toString();
    }


}
