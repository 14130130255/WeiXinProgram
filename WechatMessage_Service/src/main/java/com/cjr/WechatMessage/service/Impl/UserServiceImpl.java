package com.cjr.WechatMessage.service.Impl;

import com.cjr.WechatMessage.dao.UserDao;
import com.cjr.WechatMessage.entity.User;
import com.cjr.WechatMessage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户的业务层实体类
 * 实现了用户业务层接口
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public void add(User user) {
        userDao.insert(user);
    }

    public void edit(User user) {
        userDao.update(user);
    }

    public void delete(String openId) {
        userDao.delete(openId);
    }

    public User getByOpenId(String openId) {
        return userDao.selectByOpenId(openId);
    }
}
