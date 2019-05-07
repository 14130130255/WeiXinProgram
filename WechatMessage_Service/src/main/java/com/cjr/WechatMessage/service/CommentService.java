package com.cjr.WechatMessage.service;

public interface CommentService {

    public boolean comment(String postId, String userId, String toUserId, String contain);

}
