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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Created with qml
 * @author:qml
 * @Date:2019/4/27
 * @Time:16:53
 */
@Service("postService")
public class PostServiceImpl implements PostService {

    @Resource(name = "blinddatePostDao")
    private BlinddatePostDao blinddatePostDao;

    @Resource(name = "employmentPostDao")
    private EmploymentPostDao employmentPostDao;

    @Resource(name = "transactionPostDao")
    private TransactionPostDao transactionPostDao;

    @Autowired
    private UserService userService;

    @Resource(name = "newsPostDao")
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
        List<Post> posts = new ArrayList<Post>();
        User user = userService.getByOpenId(openId);
        List<Post> posts1 = null;
        List<Post> posts2 = null;
        List<Post> posts3 = null;
        List<Post> posts4 = null;
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
                posts1 = selectAll(1, index);
                posts2 = selectAll(2, index);
                posts3 = selectAll(3, index);
                posts4 = selectAll(4, index);
                break;
            case 1:
                posts4 = selectAll(4, index);
                break;
            case 2:
                posts3 = selectAll(3, index);
                break;
            case 3:
                posts4 = selectAll(4, index);
                posts3 = selectAll(3, index);
                break;
            case 4:
                posts2 = selectAll(2, index);
                break;
            case 5:
                posts2 = selectAll(2, index);
                posts4 = selectAll(4, index);
                break;
            case 6:
                posts2 = selectAll(2, index);
                posts3 = selectAll(3, index);
                break;
            case 7:
                posts2 = selectAll(2, index);
                posts3 = selectAll(3, index);
                posts4 = selectAll(4, index);
                break;
            case 8:
                posts1 = selectAll(1, index);
                break;
            case 9:
                posts1 = selectAll(1, index);
                posts4 = selectAll(4, index);
                break;
            case 10:
                posts1 = selectAll(1, index);
                posts3 = selectAll(3, index);
                break;
            case 11:
                posts1 = selectAll(1, index);
                posts3 = selectAll(3, index);
                posts4 = selectAll(4, index);
                break;
            case 12:
                posts1 = selectAll(1, index);
                posts2 = selectAll(2, index);
                break;
            case 13:
                posts1 = selectAll(1, index);
                posts2 = selectAll(2, index);
                posts4 = selectAll(4, index);
                break;
            case 14:
                posts1 = selectAll(1, index);
                posts2 = selectAll(2, index);
                posts3 = selectAll(3, index);
                break;
            case 15:
                posts1 = selectAll(1, index);
                posts2 = selectAll(2, index);
                posts3 = selectAll(3, index);
                posts4 = selectAll(4, index);
                break;
            default:
                break;
        }
        if (posts1 != null) {
            System.out.println("非空");
            posts.addAll(posts1);
        }
        if (posts2 != null) {
            posts.addAll(posts2);
        }
        if (posts3 != null) {
            posts.addAll(posts3);
        }
        if (posts4 != null) {
            posts.addAll(posts4);
        }

        if ( posts == null ||index * postNumOfPage >= posts.size()) {
            return null;
        }
        Collections.sort(posts);
        return posts.subList(index * postNumOfPage, (index+1)*postNumOfPage>=posts.size() ? posts.size()-1 : (index+1)*postNumOfPage);
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
