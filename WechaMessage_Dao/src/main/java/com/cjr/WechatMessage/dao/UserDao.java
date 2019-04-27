package com.cjr.WechatMessage.dao;

import com.cjr.WechatMessage.entity.User;
import org.springframework.stereotype.Repository;

/**
 * User的Dao接口
 */
@Repository("userDao")
public interface UserDao {
    /**
     * 往数据库里面添加用户
     * @param user 用户
     */
    public void insert(User user);

    /**
     * 更新数据库里面的用户
     * @param user 用户
     */
    public void update(User user);

    /**
     * 根据openId删除数据库中的用户
     * @param openId 用户的唯一标示
     */
    public void delete(String openId);

    /**
     * 根据openId查找到数据库中的用户
     * @param openId 用户的唯一标示
     * @return
     */
    User selectByOpenId(String openId);
}
