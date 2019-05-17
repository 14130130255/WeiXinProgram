package com.cjr.WechatMessage.dao;

import com.cjr.WechatMessage.entity.PostAndPicture;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Created with qml
 * @author:qml
 * @Date:2019/5/8
 * @Time:13:37
 */
@Repository("postAndPictureDao")
public interface PostAndPictureDao {
    /**
     * 插入帖子相关的图片信息
     * @param postAndPicture
     */
    public void insert(PostAndPicture postAndPicture);

    /**
     * 更新帖子图片
     * @param postAndPicture
     */
    public void update(PostAndPicture postAndPicture);

    /**
     * 根据帖子id删除帖子图片信息
     * @param postId
     */
    public void delete(String postId);

    /**
     * 根据帖子id，以及图片在数据库表中对应的名字，删除某一张图片
     * @param postId
     * @param picturename
     */
    public void deleteOnePicture(String postId, String picturename);

    /**
     * 根据帖子id查询其所有图片
     * @param postId
     * @return
     */
    public PostAndPicture select(String postId);

    /**
     * 根据帖子id和图片对应的表的属性名查询某一张图片
     * @param postId
     * @param picturename
     * @return
     */
    public String selectOne(String postId, String picturename);
}
