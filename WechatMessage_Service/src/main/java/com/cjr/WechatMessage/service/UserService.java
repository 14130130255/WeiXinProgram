package com.cjr.WechatMessage.service;

import com.cjr.WechatMessage.entity.User;
import org.springframework.stereotype.Service;

/**
 * 用户的业务层接口
 */

public interface UserService {
    /**
     * 添加用户
     * @param user
     */
    public void add(User user);

    /**
     * 修改用户
     * @param user
     */
    public void edit(User user);

    /**
     * 删除用户
     * @param openId
     */
    public void delete(String openId);

    /**
     * 通过openid查询用户
     * @param openId
     * @return
     */
    User getByOpenId(String openId);
}
