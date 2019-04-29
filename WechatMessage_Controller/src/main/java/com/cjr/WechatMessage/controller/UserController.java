package com.cjr.WechatMessage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller("userController")
public class UserController {

    @ResponseBody
    @RequestMapping("/login")
    public Map<String,Object> doLogin(Model model,
                                      @RequestParam(value="code",required = false)String code,
                                      @RequestParam(value="rawData",required = false)String rawData,
                                      @RequestParam(value="signature",required = false)String signature,
                                      @RequestParam(value="encrypteData",required = false)String encrypteData,
                                      @RequestParam(value="iv",required = false)String iv)
    {
        System.out.println("test");
        Map<String,Object> map = new HashMap<String, Object>();
        System.out.println("用户非敏感信息"+rawData);
        System.out.println("签名"+signature);
        System.out.println("code"+code);
        System.out.println("用户敏感信息"+encrypteData);
        System.out.println("iv"+iv);

        return map;
    }







}
