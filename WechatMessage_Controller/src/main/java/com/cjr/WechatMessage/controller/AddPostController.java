package com.cjr.WechatMessage.controller;

import com.cjr.WechatMessage.entity.*;
import com.cjr.WechatMessage.service.Impl.BlinddatePostServiceImpl;
import com.cjr.WechatMessage.service.Impl.EmploymentPostServiceImpl;
import com.cjr.WechatMessage.service.Impl.TransactionPostServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller("addPostController")
public class AddPostController {
    @Resource(name = "blinddatePostService")
    private BlinddatePostServiceImpl blinddatePostService;
    @Resource(name = "employmentPostService")
    private EmploymentPostServiceImpl employmentPostService;
    @Resource(name = "transactionPostService")
    private TransactionPostServiceImpl transactionPostService;

    @ResponseBody
    @RequestMapping("/addpost")
    public Map<String,String> doAddPost(Model model,
                                        @RequestParam(value="openid",required = true)String userId,
                                        @RequestParam(value = "mode",required = true)String postType,
                                        @RequestParam(value = "content",required = true)String postContent,
                                        @RequestParam(value = "is_anonymous",required = false)String isAnonymous,
                                        @RequestParam(value = "photos",required = false)String postPhotos){

        Post post;
        Map<String,String> map = new HashMap<String, String>();
        if(postType.equals(String.valueOf(Common.Blinddate.hashCode()))){
            post = new BlinddatePost();
            post.setPostType(Common.Blinddate.hashCode());
        }else if(postType.equals(String.valueOf(Common.Employment.hashCode()))){
            post = new EmploymentPost();
            post.setPostType(Common.Employment.hashCode());
        }else if(postType.equals(String.valueOf(Common.Transaction.hashCode()))){
            post = new TransactionPost();
            post.setPostType(Common.Transaction.hashCode());
        } else{
            post = null;

            map.put("type","false");
            return map;
        }

        post.setUserId(userId);
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        returnStr = f.format(date);
        post.setPostCreateTime(date);
        post.setPostId(userId+returnStr);
        post.setPostContent(postContent);
        if(postPhotos!=null){
            post.setPostPhotos(postPhotos);
        }
        else {
            post.setPostPhotos(null);
        }
        post.setLookPeopleNum(0);
        post.setPostCommentNum(0);
        post.setPostLikeNum(0);
        if (isAnonymous.equals("true")){
            post.setAnonymous(true);
        }else {
            post.setAnonymous(false);
        }
        boolean flag;

        if(post.getPostType()==Common.Blinddate.hashCode()){
            flag = blinddatePostService.addBlinddatePost(post);
        }else if(post.getPostType()==Common.Employment.hashCode()){
            flag = employmentPostService.addEmploymentPost(post);
        }else {
            flag = transactionPostService.addTransactionPost(post);
        }

        if(flag){
            map.put("success","true");
        }else {
            map.put("success","false");
        }

        return map;
    }

}
