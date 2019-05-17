package com.cjr.WechatMessage.controller;

import com.cjr.WechatMessage.entity.CommentAndUser;
import com.cjr.WechatMessage.service.CommentService;
import com.cjr.WechatMessage.service.Impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller("commentController")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping("/comment")
    public Map<String,String> comment(Model model,
                                      @RequestParam(value = "postid" ,required = true)String postId,
                                      @RequestParam(value = "posttype",required = true)Integer postType,
                                      @RequestParam(value = "openid",required = true)String CommentUserId,
                                      @RequestParam(value = "touserid",required = false)String toCommentUserId,
                                      @RequestParam(value = "content",required = true)String CommentContent){
        CommentAndUser commentAndUser = new CommentAndUser();

        commentAndUser.setPostId(postId);
        commentAndUser.setPostType(postType);
        commentAndUser.setCommentUserId(CommentUserId);
        commentAndUser.setToCommentUserId(toCommentUserId);
        commentAndUser.setCommentContent(CommentContent);
        Map<String,String> map = new HashMap<>();
        if(commentService.comment(commentAndUser)){
            map.put("comment","success");

        }else {
            map.put("comment","failed");
        }
        return map;

    }
}
