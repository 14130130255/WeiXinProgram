package com.cjr.WechatMessage.controller;

import com.cjr.WechatMessage.entity.*;
import com.cjr.WechatMessage.global.DateUtil;
import com.cjr.WechatMessage.global.UrlUtil;
import com.cjr.WechatMessage.service.BlinddatePostService;
import com.cjr.WechatMessage.service.EmploymentPostService;
import com.cjr.WechatMessage.service.NoticePostService;
import com.cjr.WechatMessage.service.TransactionPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller("addPostController")
public class AddPostController {

    @Autowired
    private BlinddatePostService blinddatePostService;
    @Autowired
    private EmploymentPostService employmentPostService;
    @Autowired
    private TransactionPostService transactionPostService;
    @Autowired
    private NoticePostService noticePostService;

    @Autowired
    private PostAndPictureController postAndPictureController;

    @ResponseBody
    @RequestMapping("/addpost")
    public Map<String, String> doAddPost(Model model,
                                         @RequestParam(value = "skey", required = true) String userId,
                                         @RequestParam(value = "mode", required = true) String postType,
                                         @RequestParam(value = "content", required = true) String postContent,
                                         @RequestParam(value = "is_anonymous", required = false) String isAnonymous,
                                         @RequestParam(value = "photos", required = false) String[] postPhotos) {

        System.out.println("openid:" + userId);
        System.out.println("mode:" + postType);
        System.out.println("content:" + postContent);
        System.out.println("is_anonymous:" + isAnonymous);
        System.out.println("photos:" + postPhotos.toString());

        System.out.println("openid:"+userId);
        System.out.println("mode:"+postType);
        System.out.println("content:"+postContent);
        System.out.println("is_anonymous:"+isAnonymous);
        System.out.println("photos:"+postPhotos);

        Post post;
        Map<String, String> map = new HashMap<String, String>();
        if (postType.equals(String.valueOf(Common.Blinddate.ordinal()))) {
            System.out.println("blinddate");
            post = new BlinddatePost();
            post.setPostType(Common.Blinddate.ordinal());
        } else if (postType.equals(String.valueOf(Common.Employment.ordinal()))) {
            post = new EmploymentPost();
            post.setPostType(Common.Employment.ordinal());
        } else if (postType.equals(String.valueOf(Common.Transaction.ordinal()))) {
            post = new TransactionPost();
            post.setPostType(Common.Transaction.ordinal());
        } else if (postType.equals(String.valueOf(Common.Notice.ordinal()))) {
            post = new NewsPost();
            post.setPostType(Common.Notice.ordinal());
        } else {
            post = null;
            map.put("type", "false");
            return map;
        }

        post.setUserId(userId);
        String returnStr = null;
        Date date = new Date();
        returnStr = DateUtil.dateTimeToString(date);
        post.setPostCreateTime(date);
        post.setPostId(userId + returnStr);
        post.setPostContent(postContent);
        if (postPhotos.length > 0) {
            post.setPostPhotos("1");
            postAndPictureController.doAddPicture(userId + returnStr, postPhotos);
        } else {
            post.setPostPhotos("0");
        }
        post.setLookPeopleNum(0);
        post.setPostCommentNum(0);
        post.setPostLikeNum(0);
        if (isAnonymous.equals("true")) {
            post.setAnonymous(true);
        } else {
            post.setAnonymous(false);
        }
        boolean flag;

        if(post.getPostType()==Common.Blinddate.ordinal()){
            flag = blinddatePostService.addBlinddatePost(post);
        }else if(post.getPostType()==Common.Employment.ordinal()){
            flag = employmentPostService.addEmploymentPost(post);
        } else if (post.getPostType() == Common.Transaction.ordinal()) {
            flag = transactionPostService.addTransactionPost(post);
        } else if (post.getPostType() == Common.Notice.ordinal()) {
            flag = noticePostService.addNoticePost(post);
        } else {
            flag = false;
        }

        if (flag) {
            map.put("success", "true");
        } else {
            map.put("success", "false");
        }

        return map;
    }

}
