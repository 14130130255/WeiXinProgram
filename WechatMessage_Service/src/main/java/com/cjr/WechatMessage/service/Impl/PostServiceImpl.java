package com.cjr.WechatMessage.service.Impl;

import com.cjr.WechatMessage.dao.BlinddatePostDao;
import com.cjr.WechatMessage.dao.EmploymentPostDao;
import com.cjr.WechatMessage.dao.NewsPostDao;
import com.cjr.WechatMessage.dao.TransactionPostDao;
import com.cjr.WechatMessage.entity.Post;
import com.cjr.WechatMessage.entity.User;
import com.cjr.WechatMessage.service.PostService;

import com.cjr.WechatMessage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Created with qml
 * @author:qml
 * @Date:2019/4/27
 * @Time:16:53
 */
@Service("postService")
public class PostServiceImpl implements PostService {

    @Autowired
    private BlinddatePostDao blinddatePostDao;

    @Autowired
    private EmploymentPostDao employmentPostDao;

    @Autowired
    private TransactionPostDao transactionPostDao;

    @Autowired
    private UserService userService;

    @Autowired
    private NewsPostDao newsPostDao;

    //每页显示的帖子数
    private int postNumOfPage = 10;

    /**
     * 根据postId查询帖子（未实现）
     * @param postId
     * @return
     */
    public Post selectByPostId(String postId) {
        return null;
    }

    /**
     * 根据用户Id查询帖子（未实现）
     * @param userId
     * @return
     */
    public List<Post> selectByUserId(String userId) {
        return null;
    }

    /**
     * 查询感兴趣的帖子，并返回
     * @param openId
     * @param index
     * @return
     */
    public List<Post> selectLike(String openId, int index) {
        List<Post> posts = null;
        User user = userService.getByOpenId(openId);
        int model = user.getChoiceMode();
        /**
         * 0 无感兴趣模块
         * 1 感兴趣相亲
         * 2 感兴趣招聘
         * 3 感兴趣二手交易
         * 数字组合为感兴趣多个模块
         */
        switch (model) {
            case 0:
                posts = selectAll(1, index);
                posts.addAll(selectAll(2, index));
                posts.addAll(selectAll(3, index));
                posts.addAll(selectAll(4, index));
                break;
            case 1:
                posts = selectAll(4, index);
                break;
            case 2:
                posts = selectAll(3, index);
                break;
            case 3:
                posts = selectAll(4, index);
                posts.addAll(selectAll(3, index));
                break;
            case 4:
                posts = selectAll(2, index);
                break;
            case 5:
                posts = selectAll(2, index);
                posts.addAll(selectAll(4, index));
                break;
            case 6:
                posts = selectAll(2, index);
                posts.addAll(selectAll(3, index));
                break;
            case 7:
                posts = selectAll(2, index);
                posts.addAll(selectAll(3, index));
                posts.addAll(selectAll(4, index));
                break;
            case 8:
                posts = selectAll(1, index);
                break;
            case 9:
                posts = selectAll(1, index);
                posts.addAll(selectAll(4, index));
                break;
            case 10:
                posts = selectAll(1, index);
                posts.addAll(selectAll(3, index));
                break;
            case 11:
                posts = selectAll(1, index);
                posts.addAll(selectAll(3, index));
                posts.addAll(selectAll(4, index));
                break;
            case 12:
                posts = selectAll(1, index);
                posts.addAll(selectAll(2, index));
                break;
            case 13:
                posts = selectAll(1, index);
                posts.addAll(selectAll(2, index));
                posts.addAll(selectAll(4, index));
                break;
            case 14:
                posts = selectAll(1, index);
                posts.addAll(selectAll(2, index));
                posts.addAll(selectAll(3, index));
                break;
            case 15:
                posts = selectAll(1, index);
                posts.addAll(selectAll(2, index));
                posts.addAll(selectAll(3, index));
                posts.addAll(selectAll(4, index));
                break;
            default:
                break;
        }
        if ( posts.size() == 0 ||index * postNumOfPage >= posts.size()) {
            return null;
        }
        List<Post> subposts= posts.subList(index * postNumOfPage, (index+1)*postNumOfPage>=posts.size() ? posts.size()-1 : (index+1)*postNumOfPage);
        Collections.sort(subposts);
        return subposts;
    }

    /**
     * 查询某一类型的帖子
     * @param type
     * @param index
     * @return
     */
    public List<Post> selectAll(int type, int index) {
        List<Post> posts = null;
        switch (type){
            /**
             * 1 表示相亲贴
             * 2 表示招聘贴
             * 3 表示二手交易贴
             */
            case 1:
                posts = blinddatePostDao.selectAll();
                break;
            case 2:
                posts = employmentPostDao.selectAll();
                break;
            case 3:
                posts = transactionPostDao.selectAll();
                break;
            case 4:
                posts = newsPostDao.selectAll();
            default:
                break;
        }
        System.out.println(posts.size());

        if (posts.size() == 0 || index * postNumOfPage >= posts.size()) {
            return null;
        }
        return posts.subList(index * postNumOfPage, (index+1)*postNumOfPage>=posts.size() ? posts.size() : (index+1)*postNumOfPage);
    }
}
