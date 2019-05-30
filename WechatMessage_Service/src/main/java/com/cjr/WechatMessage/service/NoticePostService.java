package com.cjr.WechatMessage.service;

import com.cjr.WechatMessage.entity.Post;

public interface NoticePostService {
    boolean addNoticePost(Post post);

    void changeLikeNum(String postId,Boolean isClicked);
}
