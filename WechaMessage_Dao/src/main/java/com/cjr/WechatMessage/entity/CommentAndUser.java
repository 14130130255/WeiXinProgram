package com.cjr.WechatMessage.entity;

import java.util.Date;

/**
 * @Created with qml
 * @author:qml
 * @Date:2019/4/27
 * @Time:13:31
 * 帖子评论实体类
 */
public class CommentAndUser implements Comparable<CommentAndUser>{

    private String postId;

    private String commentUserId;

    private String toCommentUserId;

    private int commentId;

    private String commentContent;

    private int postType;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private Date createTime;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getToCommentUserId() {
        return toCommentUserId;
    }

    public void setToCommentUserId(String toCommentUserId) {
        this.toCommentUserId = toCommentUserId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public int compareTo(CommentAndUser o) {
        return -createTime.compareTo(o.getCreateTime());
    }
}
