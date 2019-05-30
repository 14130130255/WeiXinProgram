package com.cjr.WechatMessage.service;

import com.cjr.WechatMessage.entity.Post;

public interface EmploymentPostService {

    boolean addEmploymentPost(Post post);

    void changeLikeNum(String postId,Boolean isClicked);
}
