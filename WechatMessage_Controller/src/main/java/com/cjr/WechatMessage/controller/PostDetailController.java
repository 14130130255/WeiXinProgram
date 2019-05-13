package com.cjr.WechatMessage.controller;

import com.cjr.WechatMessage.entity.Post;
import com.cjr.WechatMessage.entity.User;
import com.cjr.WechatMessage.service.Impl.PostDetailServiceImpl;
import com.cjr.WechatMessage.service.Impl.PostViewNumberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PostDetailController {

    @Autowired
    private PostDetailServiceImpl postDetailService;
    @Autowired
    private PostViewNumberServiceImpl postViewNumberService;

    @ResponseBody
    @RequestMapping("/postDetail")
    public Map<String,String> doGetPostDetail(Model model,
                                        @RequestParam(value="postid",required = true)String postId,
                                        @RequestParam(value = "posttype",required = true)int postType){

        try{
            String returnobj;
            returnobj = postDetailService.getPostDetail(postId,postType);
            Map<String,String> map = new HashMap<>();
            if(returnobj !=null){
                map.put("postDetail",returnobj);
                //likepeople++
                postViewNumberService.numAdd(postId,postType);
            }
            else {
                map.put("postDetail","false");
            }
            return map;


        }catch (Exception e){
            Map<String,String> map = new HashMap<>();
            map.put("postDetail","false");
            return map;
        }

    }

}
