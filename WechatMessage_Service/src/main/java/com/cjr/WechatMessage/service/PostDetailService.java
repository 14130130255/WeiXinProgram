package com.cjr.WechatMessage.service;

import com.cjr.WechatMessage.entity.Post;
import com.cjr.WechatMessage.entity.User;

import java.util.Map;

public interface PostDetailService {

    public Map<String,Object> getPostDetail(String postId, int postType);

    public void addLookPeopleNum(String postId,int postType,String openid);
}
