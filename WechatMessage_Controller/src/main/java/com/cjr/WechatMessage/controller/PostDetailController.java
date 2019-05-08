package com.cjr.WechatMessage.controller;

import com.cjr.WechatMessage.entity.Post;
import com.cjr.WechatMessage.entity.User;
import com.cjr.WechatMessage.service.Impl.PostDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class PostDetailController {

    @Autowired
    private PostDetailServiceImpl postDetailService;

    @ResponseBody
    @RequestMapping("/addpost")
    public Map<String,String> doGetPostDetail(Model model,
                                        @RequestParam(value="openid",required = true)String userId,
                                        @RequestParam(value = "postid",required = true)String postId){

        Post post = new Post();
        //未定

        postDetailService.getPostDetail(post);

        return null;

    }

}
