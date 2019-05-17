package com.cjr.WechatMessage.controller;


import com.cjr.WechatMessage.qiniuPicture.QiniuUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Created with qml
 * @author:qml
 * @Date:2019/5/12
 * @Time:13:47
 */
@Controller("qiNiu")
public class QiNiu {

    /**
     * 提供七牛存储位置的uptoken
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/getQiNiuUpToken")
    public String doGetQiNiuUpToken(Model model){
        return QiniuUpload.getUpToken();
    }
}
