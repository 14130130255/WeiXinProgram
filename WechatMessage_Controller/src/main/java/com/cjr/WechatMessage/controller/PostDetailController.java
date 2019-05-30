package com.cjr.WechatMessage.controller;

import com.cjr.WechatMessage.entity.Post;
import com.cjr.WechatMessage.entity.User;
import com.cjr.WechatMessage.service.*;
import com.cjr.WechatMessage.service.Impl.PostDetailServiceImpl;
import com.cjr.WechatMessage.service.PostDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller("postDetailController")
public class PostDetailController {

    @Autowired
    private PostDetailService postDetailService;
    @Autowired
    private BlinddatePostService blinddatePostService;
    @Autowired
    private EmploymentPostService employmentPostService;
    @Autowired
    private TransactionPostService transactionPostService;
    @Autowired
    private NoticePostService noticePostService;

    @ResponseBody
    @RequestMapping("/postDetail")
    public Map<String,Object> doGetPostDetail(Model model,
                                        @RequestParam(value="postid",required = true)String postId,
                                        @RequestParam(value = "posttype",required = true)int postType,
                                        @RequestParam(value= "openid",required = false)String openid){

        System.out.println("postId:"+postId+" postType:"+postType);
        postDetailService.addLookPeopleNum(postId,postType,openid);
        Map<String,Object> map = new HashMap<>();

        map = postDetailService.getPostDetail(postId,postType);

        return map;
    }

    @ResponseBody
    @RequestMapping("/changeLikeNum")
    public String changePostLikeNum(Model model,@RequestParam(value="isClicked",required = false)Boolean isClicked,
                                    @RequestParam(value = "postType",required = false)int postType,
                                    @RequestParam(value= "postId",required = false)String postId){
        System.out.println("isClicked:"+isClicked);
        System.out.println("postType:"+postType);
        System.out.println("postId:"+postId);

        switch (postType){
            case 0:
                blinddatePostService.changeLikeNum(postId,isClicked);
                break;
            case 1:
                employmentPostService.changeLikeNum(postId,isClicked);
                break;
            case 2:
                transactionPostService.changeLikeNum(postId,isClicked);
                break;
            case 3:
                noticePostService.changeLikeNum(postId,isClicked);
                break;
        }

        return "ok";
    }
}
