package com.cjr.WechatMessage.service.Impl;

import com.cjr.WechatMessage.dao.*;
import com.cjr.WechatMessage.entity.CommentAndUser;
import com.cjr.WechatMessage.entity.Post;
import com.cjr.WechatMessage.entity.User;
import com.cjr.WechatMessage.global.DateUtil;
import com.cjr.WechatMessage.service.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private BlinddatePostDao blinddatePostDao;
    @Autowired
    private EmploymentPostDao employmentPostDao;
    @Autowired
    private TransactionPostDao transactionPostDao;
    @Autowired
    private NewsPostDao newsPostDao;


    @Autowired
    private CommentBlinddateDao commentBlinddateDao;

    @Autowired
    private CommentEmploymentDao commentEmploymentDao;

    @Autowired
    private CommentTransactionDao commentTransactionDao;

    @Autowired
    private CommentNoticeDao commentNoticeDao;
    @Autowired
    private UserDao userDao;

    public Map<String,Object> comment(CommentAndUser commentAndUser, Integer postType, String postId) {
        List<CommentAndUser> commentLists = null;
        Map<String,Object> map = new HashMap<String, Object>();
        Post post = null;
        int postCommentNum = 0;
        try{
            switch(postType){
                case 0:
                    post = blinddatePostDao.selectByPostId(postId);
                    postCommentNum = post.getPostCommentNum();
                    post.setPostCommentNum(postCommentNum+1);
                    blinddatePostDao.update(post);
                    commentBlinddateDao.insert(commentAndUser);
                    commentLists = commentBlinddateDao.selectByPostId(postId);
                    break;
                case 1:
                    post = employmentPostDao.selectByPostId(postId);
                    postCommentNum = post.getPostCommentNum();
                    post.setPostCommentNum(postCommentNum+1);
                    employmentPostDao.update(post);
                    commentEmploymentDao.insert(commentAndUser);
                    commentLists = commentEmploymentDao.selectByPostId(postId);
                    break;
                case 2:
                    post = transactionPostDao.selectByPostId(postId);
                    postCommentNum = post.getPostCommentNum();
                    post.setPostCommentNum(postCommentNum+1);
                    transactionPostDao.update(post);
                    commentTransactionDao.insert(commentAndUser);
                    commentLists = commentTransactionDao.selectByPostId(postId);
                    break;
                case 3:
                    post = newsPostDao.selectByPostId(postId);
                    postCommentNum = post.getPostCommentNum();
                    post.setPostCommentNum(postCommentNum+1);
                    newsPostDao.update(post);
                    commentNoticeDao.insert(commentAndUser);
                    commentLists = commentNoticeDao.selectByPostId(postId);
                    break;
            }
            commentlistToMap(map, commentLists);

        }catch (Exception e){
            System.out.println("comment insert error");
            e.printStackTrace();
            return null;
        }

        return map;
    }

    public void commentlistToMap(Map<String,Object> map,List<CommentAndUser> lists){
        int length = lists.size();
        int index = 0;
        JSONObject[] commentJsonObject = new JSONObject[length];
        for(CommentAndUser commentAndUser:lists){
            JSONObject jsonObject = new JSONObject();
            User commentuser = userDao.selectByOpenId(commentAndUser.getCommentUserId());
            User tocommentuser = userDao.selectByOpenId(commentAndUser.getToCommentUserId());
            jsonObject.put("postId",commentAndUser.getPostId());
            jsonObject.put("commentUserNickName",commentuser.getNickName());
            jsonObject.put("commentUserAvatar",commentuser.getAvatarUrl());
            jsonObject.put("tocommentUserNickName",tocommentuser.getNickName());
            jsonObject.put("tocommentUserAvatar",tocommentuser.getAvatarUrl());
            jsonObject.put("commentContent",commentAndUser.getCommentContent());
            jsonObject.put("postType",commentAndUser.getPostType());
            String time = DateUtil.dateTimeToString(commentAndUser.getCreateTime());
            jsonObject.put("createTime",time);
            commentJsonObject[index] = jsonObject;
            index++;
        }
        map.put("commentLists",commentJsonObject);
    }
}
