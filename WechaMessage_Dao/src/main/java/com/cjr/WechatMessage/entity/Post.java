package com.cjr.WechatMessage.entity;

import java.util.Date;

public class Post {
    /**
     *
     * `postId` varchar(255) NOT NULL,
     *   `postType` int(11) NOT NULL,
     *   `userId` varchar(127) NOT NULL,
     *   `postContent` varchar(255) DEFAULT NULL,
     *   `postPhotos` varchar(255) DEFAULT NULL,
     *   `postLikeNum` int(11) DEFAULT NULL,
     *   `postCommentNum` int(11) DEFAULT NULL,
     *   `lookPeopleNum` int(11) DEFAULT NULL,
     *   `postCreateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
     * **/


    String postId;
    int postType;
    String userId;
    String postContent;
    String postPhotos;
    int postLikeNum;
    int postCommentNum;
    int lookPeopleNum;
    Date postCreateTime;
    boolean isAnonymous;

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostPhotos() {
        return postPhotos;
    }

    public void setPostPhotos(String postPhotos) {
        this.postPhotos = postPhotos;
    }

    public int getPostLikeNum() {
        return postLikeNum;
    }

    public void setPostLikeNum(int postLikeNum) {
        this.postLikeNum = postLikeNum;
    }

    public int getPostCommentNum() {
        return postCommentNum;
    }

    public void setPostCommentNum(int postCommentNum) {
        this.postCommentNum = postCommentNum;
    }

    public int getLookPeopleNum() {
        return lookPeopleNum;
    }

    public void setLookPeopleNum(int lookPeopleNum) {
        this.lookPeopleNum = lookPeopleNum;
    }

    public Date getPostCreateTime() {
        return postCreateTime;
    }

    public void setPostCreateTime(Date postCreateTime) {
        this.postCreateTime = postCreateTime;
    }
}
