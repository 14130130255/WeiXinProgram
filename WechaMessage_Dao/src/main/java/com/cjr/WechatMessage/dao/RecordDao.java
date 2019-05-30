package com.cjr.WechatMessage.dao;

import com.cjr.WechatMessage.entity.Post;
import com.cjr.WechatMessage.entity.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Created with qml
 * @author:qml
 * @Date:2019/4/27
 * @Time:14:17
 */
@Repository()
public interface RecordDao {

    public void insert(Record record);

    public List<Record> selectByPostId(String postId);

    public Record selectByImageUrl(String imageUrl,String postId);
}
