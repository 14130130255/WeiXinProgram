package com.cjr.WechatMessage.service;


import com.cjr.WechatMessage.entity.Record;

import java.util.List;

public interface RecordService {
    public void add(Record record);

    public List<Record> getByPostId(String postId);

    public Record getByImageUrl(String imageUrl,String postId);
}
