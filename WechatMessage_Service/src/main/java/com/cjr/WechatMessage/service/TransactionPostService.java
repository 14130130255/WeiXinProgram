package com.cjr.WechatMessage.service;

import com.cjr.WechatMessage.entity.Post;

public interface TransactionPostService {

    boolean addTransactionPost(Post post);

    void changeLikeNum(String postId,Boolean isClicked);
}
