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

    public boolean comment(CommentAndUser commentAndUser) {


        try{
            commentAndUserDao.insert(commentAndUser);
        }catch (Exception e){
            System.out.println("comment insert error");
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
