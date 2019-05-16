package com.cjr.WechatMessage.controller;

import com.cjr.WechatMessage.entity.PostAndPicture;
import com.cjr.WechatMessage.service.Impl.PostAndPictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Created with qml
 * @author:qml
 * @Date:2019/5/12
 * @Time:13:59
 */
@Controller("postAndPictureController")
public class PostAndPictureController {

    @Autowired
    private PostAndPictureServiceImpl postAndPictureService;

    @Autowired
    private PostAndPicture postAndPicture;

    /**
     * 非接口
     * 往数据库里插入帖子图片
     * 该方法可直接被帖子controller里的方法调用，存储相应的帖子图片
     * @param postId
     * @param pictures
     */
    public void doAddPicture(String postId, String[] pictures) {

        postAndPicture.setPostId(postId);
        int length = pictures.length;
        if (length < 6) {
            for (int i=length; i < 6; i++) {
                pictures[i] = null;
            }
        }
        postAndPicture.setPicture1(pictures[0]);
        postAndPicture.setPicture2(pictures[1]);
        postAndPicture.setPicture3(pictures[2]);
        postAndPicture.setPicture4(pictures[3]);
        postAndPicture.setPicture5(pictures[4]);
        postAndPicture.setPicture6(pictures[5]);

        postAndPictureService.insert(postAndPicture);
    }

    /**
     * 根据帖子Id删除所有图片
     * @param model
     * @param postId
     */
    @ResponseBody
    @RequestMapping("/deleteByPostId")
    public void doDeleteByPostId(Model model,
                               @RequestParam(value = "psotId", required = true)String postId) {
        postAndPictureService.deleteByPostId(postId);

    }

    /**
     * 根据帖子Id查找所有图片
     * @param model
     * @param postId
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectByPostId")
    public Map<String, String> doSelectByPostId(Model model,
                                                @RequestParam(value = "psotId", required = true)String postId) {
        postAndPicture = postAndPictureService.selectByPortId(postId);
        Map<String, String> pictures = new HashMap<>();
        pictures.put("picture1", postAndPicture.getPicture1());
        pictures.put("picture2", postAndPicture.getPicture2());
        pictures.put("picture3", postAndPicture.getPicture3());
        pictures.put("picture4", postAndPicture.getPicture4());
        pictures.put("picture5", postAndPicture.getPicture5());
        pictures.put("picture6", postAndPicture.getPicture6());

        return pictures;
    }

    /**
     * 非接口
     * 根据postId查询图片
     * @param postId
     * @return
     */
    public String[] selectByPostId(String postId) {
        postAndPicture = postAndPictureService.selectByPortId(postId);
        String pictures[] = new String[6];
        pictures[0] = postAndPicture.getPicture1();
        pictures[1] = postAndPicture.getPicture2();
        pictures[2] = postAndPicture.getPicture3();
        pictures[3] = postAndPicture.getPicture4();
        pictures[4] = postAndPicture.getPicture5();
        pictures[5] = postAndPicture.getPicture6();

        return pictures;
    }
}
