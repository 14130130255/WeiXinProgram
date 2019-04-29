package com.cjr.WechatMessage.controller;

import com.cjr.WechatMessage.entity.Post;

import com.cjr.WechatMessage.service.Impl.PostServiceImpl;
import com.cjr.WechatMessage.service.Impl.UserServiceImpl;
import com.cjr.WechatMessage.service.PostService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Created with qml
 * @author:qml
 * @Date:2019/4/27
 * @Time:17:02
 */
@Controller("showController")
public class ShowController {

    @Autowired
    private PostService postService;

    @ResponseBody
    @RequestMapping("/showLikePost")
    public Map<String,Object> doShowLikePost(Model model,
                                      @RequestParam(value="openId",required = false)String openId,
                                      @RequestParam(value = "index",required = false)int index) {
        List<Post> posts = postService.selectLike(openId, index);
        Map<String,Object> map = new HashMap<String, Object>();
        listToMap(posts, map);
        return map;
    }

    @RequestMapping("/showBlindDatePost")
    public Map<String,Object> doShowBlindDatePost(Model model,
                                      @RequestParam(value="index",required = false)int index) {
        List<Post> posts = postService.selectAll(1, index);
        Map<String,Object> map = new HashMap<String, Object>();
        listToMap(posts, map);
        return map;
    }

    @RequestMapping("/showEmploymentPost")
    public Map<String,Object> doShowEmploymentPost(Model model,
                                                  @RequestParam(value="index",required = false)int index) {
        List<Post> posts = postService.selectAll(2, index);
        Map<String,Object> map = new HashMap<String, Object>();
        listToMap(posts, map);
        return map;
    }

    @RequestMapping("/showTransactionPost")
    public Map<String,Object> doShowTransactionPost(Model model,
                                                  @RequestParam(value="index",required = false)int index) {
        List<Post> posts = postService.selectAll(3, index);
        Map<String,Object> map = new HashMap<String, Object>();
        listToMap(posts, map);
        return map;
    }

    public void listToMap(List<Post> posts, Map<String, Object> map) {
        int index = 0;
        for (Post post : posts){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("postId", post.getPostId());
            jsonObject.put("postType", post.getPostType());
            jsonObject.put("userId", post.getUserId());
            jsonObject.put("postContent", post.getPostContent());
            jsonObject.put("postPhotos", post.getPostPhotos());
            jsonObject.put("postLikeNum", post.getPostLikeNum());
            jsonObject.put("postCommentNum", post.getPostCommentNum());
            jsonObject.put("lookPeopleNum", post.getLookPeopleNum());
            jsonObject.put("postCreateTime", post.getPostCreateTime());
            jsonObject.put("isAnonymous", post.isAnonymous());
            map.put(Integer.toString(index), jsonObject);
            index++;
        }

    }
}
