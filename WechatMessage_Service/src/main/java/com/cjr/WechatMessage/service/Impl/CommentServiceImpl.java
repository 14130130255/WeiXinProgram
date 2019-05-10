package com.cjr.WechatMessage.service.Impl;

import com.cjr.WechatMessage.dao.CommentAndUserDao;
import com.cjr.WechatMessage.entity.CommentAndUser;
import com.cjr.WechatMessage.service.CommentService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentAndUserDao commentAndUserDao;

    public boolean comment(String postId, String userId, String toUserId, String contain) {


        return false;
    }
}
