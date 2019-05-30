package com.cjr.WechatMessage.service;

import com.cjr.WechatMessage.entity.CommentAndUser;

import java.util.Map;

public interface CommentService {

    public Map<String,Object> comment(CommentAndUser commentAndUser, Integer postType, String postId);

}
