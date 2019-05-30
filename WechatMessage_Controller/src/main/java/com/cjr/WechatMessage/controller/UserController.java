package com.cjr.WechatMessage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cjr.WechatMessage.entity.User;
import com.cjr.WechatMessage.global.UrlUtil;
import com.cjr.WechatMessage.service.UserService;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller("userController")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取openid和sessionkey
     * @param code 小程序传过来的code凭证
     * @return
     */
    public static JSONObject getSessionKeyAndOpenid(String code){
        //微信登录code
        String wxCode = code;
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        String appid = "wxffc3c46e2f269822";//小程序id
        String secret = "db20fc00789aaa5bc22359a65c4cb851";//小程序秘钥
        String js_code = wxCode;//小程序端返回的code
        String grant_type = "authorization_code";//默认参数

        String requestUrlParam = "appid="+appid+"&"+"secret="+secret+"&"+
                                 "js_code="+js_code+"&"+"grant_type="+grant_type;

        //调用UrlUtil中的sendPost方法
        JSONObject jsonObject = JSON.parseObject(UrlUtil.sendPost(requestUrl,requestUrlParam));
        return jsonObject;
    }

    /**
     * 登录
     * @param model
     * @param code
     * @param rawData
     * @param signature
     * @param encrypteData
     * @param iv
     * @return result openid
     */
    @ResponseBody
    @RequestMapping("/login")
    public Map<String,Object> doLogin(Model model,
                                      @RequestParam(value="code",required = false)String code,
                                      @RequestParam(value="rawData",required = false)String rawData,
                                      @RequestParam(value="signature",required = false)String signature,
                                      @RequestParam(value="encrypteData",required = false)String encrypteData,
                                      @RequestParam(value="iv",required = false)String iv)
    {
        System.out.println("签名:"+signature);
        System.out.println("code:"+code);
        System.out.println("用户敏感信息:"+encrypteData);
        System.out.println("iv:"+iv);
        System.out.println("用户非敏感信息:"+rawData);

        Map<String,Object> map = new HashMap<String, Object>();


        //将前台传过来的rawData字符串转化为json对象
        JSONObject rawDataJson = JSON.parseObject(rawData);

        //获取包含openid和sessionkey的json对象
        JSONObject sessionKeyAndOpenid = getSessionKeyAndOpenid(code);

        System.out.println("post请求获取的sessionkey和openid："+sessionKeyAndOpenid);

        //获取openid
        String openid = sessionKeyAndOpenid.getString("openid");
        //获取sessionkey
        String sessionkey = sessionKeyAndOpenid.getString("session_key");

        System.out.println("openid="+openid+",session_key="+sessionkey);

        User user = userService.getByOpenId(openid);
        //uuid生成唯一key
        String skey = UUID.randomUUID().toString();

        String nickName = rawDataJson.getString("nickName");
        String gender = rawDataJson.getString("gender");
        String avatarUrl = rawDataJson.getString("avatarUrl");
        String city = rawDataJson.getString("city");
        String country = rawDataJson.getString("country");
        String province = rawDataJson.getString("province");

        if(user==null){
            System.out.println("用户openid不存在，插入数据");
            user = new User();
            user.setAddress(country+" "+province+" "+city);
            user.setAvatarUrl(avatarUrl);
            user.setCreateTime(new Date());
            user.setGender(gender);
            user.setNickName(nickName);
            user.setOpenId(openid);
            user.setSessionKey(sessionkey);
            user.setSkey(skey);
            userService.add(user);
        }else{
            System.out.println("用户openid已存在，不需要插入，更新数据");
            user.setAddress(country+" "+province+" "+city);
            user.setAvatarUrl(avatarUrl);
            user.setCreateTime(new Date());
            user.setGender(gender);
            user.setNickName(nickName);
            user.setSessionKey(sessionkey);
            user.setSkey(skey);

            userService.edit(user);
            if (user.getStudentNumber() != null) {
                map.put("studentNumber", true);
            }else {
                map.put("studentNumber", false);
            }
            if (user.getChoiceMode() != null) {
                map.put("ChoiceMode", true);
            }else {
                map.put("ChoiceMode", false);
            }
        }

        map.put("result",0);
        map.put("openid",openid);

        return map;
    }

    /**
     * 验证
     * @param model
     * @param school
     * @param collage
     * @param degree
     * @param studentNumber
     * @return result
     */
    @ResponseBody
    @RequestMapping("/authentication")
    public Map<String,String> doAuthentication(Model model,
                                       @RequestParam(value="openid",required = false)String openid,
                                       @RequestParam(value="school",required = false)String school,
                                       @RequestParam(value="collage",required = false)String collage,
                                       @RequestParam(value="degree",required = false)String degree,
                                       @RequestParam(value="studentNumber",required = false)String studentNumber)
    {
        Map<String,String> map  = new HashMap<String, String>();
        System.out.println("openid:"+openid);
        System.out.println("school:"+school);
        System.out.println("collage:"+collage);
        System.out.println("degree:"+degree);
        System.out.println("studentNumber:"+studentNumber);
        //通过openid查找到对应的用户
        User user = userService.getByOpenId(openid);
        if(user == null){
            System.out.println("不存在用户");
        }
        else{
            System.out.println("更新学生验证信息成功");

            user.setCollege(collage);
            user.setDegree(degree);
            user.setSchool(school);
            user.setStudentNumber(studentNumber);

            userService.edit(user);
        }

        map.put("result","0");
        return map;
    }

    /**
     * 关注
     * @param model
     * @param choiceMode
     * @return result
     */
    @ResponseBody
    @RequestMapping("/follow")
    public Map<String,String> doFollow(Model model,
                                       @RequestParam(value="openid",required = false)String openid,
                                       @RequestParam(value="choiceMode",required = false)String choiceMode)

    {
        Map<String,String> map = new HashMap<String,String>();
        //验证前台传过来的数据是否正确
        System.out.println("openid:"+openid);
        System.out.println("choiceMode:"+choiceMode);

        //通过openid查找到对应的用户
        User user = userService.getByOpenId(openid);
        if(user == null){
            System.out.println("不存在用户");
        }
        else{
            System.out.println("更新用户choiceMode成功");

            user.setChoiceMode(Integer.parseInt(choiceMode));

            userService.edit(user);
        }

        map.put("result","0");

        return map;
    }





}
