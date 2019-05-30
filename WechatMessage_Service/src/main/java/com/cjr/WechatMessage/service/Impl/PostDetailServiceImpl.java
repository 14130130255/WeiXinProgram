package com.cjr.WechatMessage.service.Impl;

import com.cjr.WechatMessage.dao.*;
import com.cjr.WechatMessage.entity.*;
import com.cjr.WechatMessage.global.DateUtil;
import com.cjr.WechatMessage.service.PostAndPictureService;
import com.cjr.WechatMessage.service.PostDetailService;
import com.cjr.WechatMessage.service.UserService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
*
* 返回帖子细节，以及评论
* {“post”:{...},"commentAndUser":{...}}
*
* */
@Service("postDetailService")
public class PostDetailServiceImpl implements PostDetailService {

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

    @Autowired
    private BlinddatePostDao blinddatePostDao;
    @Autowired
    private EmploymentPostDao employmentPostDao;
    @Autowired
    private TransactionPostDao transactionPostDao;
    @Autowired
    private NewsPostDao newsPostDao;
    @Autowired
    private RecordDao recordDao;

    public void addLookPeopleNum(String postId,int postType,String openid){
        Post post = null;
        Record record = null;
        User user = userDao.selectByOpenId(openid);
        System.out.println(user.getAvatarUrl());
        record = recordDao.selectByImageUrl(user.getAvatarUrl(),postId);

        if(record==null) {
            record = new Record();
            record.setImageUrl(user.getAvatarUrl());
            record.setPostId(postId);
            recordDao.insert(record);
            switch(postType){
                case 0:
                    //点击进入详情页，帖子浏览人数增加1
                    post = blinddatePostDao.selectByPostId(postId);
                    post.setLookPeopleNum(post.getLookPeopleNum()+1);
                    blinddatePostDao.update(post);
                    break;
                case 1:
                    //点击进入详情页，帖子浏览人数增加1
                    post = employmentPostDao.selectByPostId(postId);
                    post.setLookPeopleNum(post.getLookPeopleNum()+1);
                    employmentPostDao.update(post);
                    break;
                case 2:
                    //点击进入详情页，帖子浏览人数增加1
                    post = transactionPostDao.selectByPostId(postId);
                    post.setLookPeopleNum(post.getLookPeopleNum()+1);
                    transactionPostDao.update(post);
                    break;
                case 3:
                    //点击进入详情页，帖子浏览人数增加1
                    post = newsPostDao.selectByPostId(postId);
                    post.setLookPeopleNum(post.getLookPeopleNum()+1);
                    newsPostDao.update(post);
                    break;
            }
        }else{
            System.out.println("头像已存在");
        }
    }


    public Map<String,Object> getPostDetail(String postId, int postType) {

        Map<String,Object> map = new HashMap<String, Object>();


        List<CommentAndUser> commentLists = null;

        List<Record> records = recordDao.selectByPostId(postId);

        switch(postType){
            case 0:
                //根据帖子id获取评论列表
                commentLists = commentBlinddateDao.selectByPostId(postId);
                break;
            case 1:
                //根据帖子id获取评论列表
                commentLists = commentEmploymentDao.selectByPostId(postId);
                break;
            case 2:
                //根据帖子id获取评论列表
                commentLists = commentTransactionDao.selectByPostId(postId);
                break;
            case 3:
                //根据帖子id获取评论列表
                commentLists = commentNoticeDao.selectByPostId(postId);
                break;
        }
        commentlistToMap(map, commentLists);
        recordlistToMap(map,records);

        return map;
    }
    public void recordlistToMap(Map<String,Object> map,List<Record> lists){
        int length = lists.size();
        String[] avatarArray = new String[length];
        int index = 0;
        JSONObject jsonObject = new JSONObject();
        for(Record record:lists){
            avatarArray[index] = record.getImageUrl();
            index++;
        }
        jsonObject.put("avatarArray",avatarArray);
        map.put("avatarData",jsonObject);
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
