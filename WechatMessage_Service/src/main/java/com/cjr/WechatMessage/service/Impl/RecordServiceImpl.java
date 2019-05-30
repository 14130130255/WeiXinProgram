package com.cjr.WechatMessage.service.Impl;

import com.cjr.WechatMessage.dao.RecordDao;
import com.cjr.WechatMessage.entity.Record;
import com.cjr.WechatMessage.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("recordService")
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordDao recordDao;

    public void add(Record record) {
        recordDao.insert(record);
    }

    public List<Record> getByPostId(String postId) {
        return recordDao.selectByPostId(postId);
    }

    public Record getByImageUrl(String imageUrl,String postId) {
        return recordDao.selectByImageUrl(imageUrl,postId);
    }
}
