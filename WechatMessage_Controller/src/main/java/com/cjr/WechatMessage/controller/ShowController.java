package com.cjr.WechatMessage.controller;

import com.cjr.WechatMessage.entity.Post;


import com.cjr.WechatMessage.entity.User;
import com.cjr.WechatMessage.global.DateUtil;
import com.cjr.WechatMessage.service.Impl.PostServiceImpl;
import com.cjr.WechatMessage.service.Impl.UserServiceImpl;
import com.cjr.WechatMessage.service.PostService;
import com.cjr.WechatMessage.service.UserService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Autowired
    private UserService userService;
    @Autowired
    private PostAndPictureController postAndPictureController;

    @ResponseBody
    @RequestMapping("/getInitData")
    public Map<String,Object> doGetInitData(Model model,@RequestParam(value="index",required = false)int index){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Post> bindDatePosts = postService.selectAll(1,index);
        List<Post> employmentPosts = postService.selectAll(2,index);
        List<Post> transactionPosts = postService.selectAll(3,index);
        if(bindDatePosts==null){
            map.put("databindDate",null);
        }
        else{
            System.out.println("111");
            listToMap(bindDatePosts,map,"bindDate");
        }
        if(employmentPosts==null){
            map.put("dataemployment",null);
        }
        else{
            listToMap(employmentPosts,map,"employment");
        }
        if(transactionPosts==null){
            map.put("datatransaction",null);
        }
        else{
            listToMap(transactionPosts,map,"transaction");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/showLikePost")
    /**
     * 获取感兴趣模块的帖子
     * opendId为用户id
     * index为页面展示的第几页
     */
    public Map<String,Object> doShowLikePost(Model model,
                                      @RequestParam(value="openId",required = false)String openId,
                                      @RequestParam(value = "index",required = false)int index) {
        List<Post> posts = postService.selectLike(openId, index);
        Map<String,Object> map = new HashMap<String, Object>();
        listToMap(posts, map,"like");
        return map;
    }

    @ResponseBody
    @RequestMapping("/showBlindDatePost")
    /**
     * 获取相亲模块的帖子
     * index：页面展示的第几页
     */
    public Map<String,Object> doShowBlindDatePost(Model model,
                                      @RequestParam(value="index",required = false)int index) {
        System.out.println(index);
        List<Post> posts = postService.selectAll(1, index);
        Map<String,Object> map = new HashMap<String, Object>();
        if(posts==null){
            map.put("databindDate",null);
        }else{
            listToMap(posts, map,"bindDate");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/showEmploymentPost")
    /**
     * 获取招聘模块的帖子
     * index：页面展示的第几页
     */
    public Map<String,Object> doShowEmploymentPost(Model model,
                                                  @RequestParam(value="index",required = false)int index) {
        System.out.println(index);
        List<Post> posts = postService.selectAll(2, index);
        Map<String,Object> map = new HashMap<String, Object>();
        if(posts==null){
            map.put("dataemployment",null);
        }else{
            listToMap(posts, map,"employment");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/showTransactionPost")
    /**
     * 获取交易模块的帖子
     * index：页面展示的第几页
     */
    public Map<String,Object> doShowTransactionPost(Model model,
                                                  @RequestParam(value="index",required = false)int index) {
        System.out.println(index);
        List<Post> posts = postService.selectAll(3, index);
        Map<String,Object> map = new HashMap<String, Object>();
        if(posts==null){
            map.put("datatransaction",null);
        }else{
            listToMap(posts, map,"transaction");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/showNewsPost")
    public Map<String,Object> doShowNewsPost(Model model,
                                             @RequestParam(value="index",required = false)int index){
        System.out.println(index);
        List<Post> posts = postService.selectAll(4,index);
        Map<String,Object> map = new HashMap<>();
        if(posts==null){
            map.put("datanews",null);
        }else{
            listToMap(posts,map,"news");
        }
        return map;
    }


    /**
     * 将list中的帖子对象属性转换存储在map中
     * @param posts
     * @param map
     */
    public void listToMap(List<Post> posts, Map<String, Object> map,String type) {
        JSONObject[] jsonObjects = new JSONObject[5];
        int index = 0;
        System.out.println(posts.size());
        for (Post post : posts){
            String photos[] = new String[6];
            JSONObject jsonObject = new JSONObject();
            if(post.isAnonymous()==true){
                jsonObject.put("nickName","匿名用户");
            }else{
                User user = userService.getByOpenId(post.getUserId());
                jsonObject.put("nickName",user.getNickName());
            }

            String time = DateUtil.dateTimeToString(post.getPostCreateTime());
            jsonObject.put("postId", post.getPostId());
            jsonObject.put("postType", post.getPostType());
            jsonObject.put("userId", post.getUserId());
            jsonObject.put("postContent", post.getPostContent());
            if (Integer.parseInt(post.getPostPhotos()) == 1) {
                photos = postAndPictureController.selectByPostId(post.getPostId());
            }else {
                for (int i=0; i < 6; i++) {
                    photos[i] = null;
                }
            }
            jsonObject.put("postPhotos", photos);
//            jsonObject.put("postPhotos", post.getPostPhotos());
            jsonObject.put("postLikeNum", post.getPostLikeNum());
            jsonObject.put("postCommentNum", post.getPostCommentNum());
            jsonObject.put("lookPeopleNum", post.getLookPeopleNum());
            jsonObject.put("postCreateTime", time);
            jsonObject.put("isAnonymous", post.isAnonymous());
            jsonObjects[index] = jsonObject;
            index++;
        }
        map.put("data"+type, jsonObjects);
    }
}

