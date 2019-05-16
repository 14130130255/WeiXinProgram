package com.cjr.WechatMessage.qiniuPicture;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * @Created with qml
 * @author:qml
 * @Date:2019/5/9
 * @Time:9:01
 */
@Component("qiniuUpload")
public class QiniuUpload {

    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static String ACCESS_KEY = QiniuUtils.accessKey; //这两个登录七牛 账号里面可以找到
    private static String SECRET_KEY = QiniuUtils.secretKey;

    //要上传的空间
    private static String bucketname = QiniuUtils.bucket; //对应要上传到七牛上 你的那个路径（自己建文件夹 注意设置公开）

    //秘钥配置
    private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    private static Configuration configuration = new Configuration(Zone.huabei());

    //创建上传对象
    private static UploadManager uploadManager = new UploadManager(configuration);

    /**
     * 简单上传，使用默认策略，只需要设置上传的空间名就可以了
     */
    public static String getUpToken(){
        return auth.uploadToken(bucketname);
    }

    /**
     * 根据图片的绝对路径来保存的
     * @param FilePath  参数就是图片的绝对路径
     * @param FileName  保存在七牛云空间的图片名称。
     * @return
     */
    public static String UploadPic(String FilePath,String FileName){
//        Configuration cfg = new Configuration(Zone.huabei());
//        UploadManager uploadManager = new UploadManager(cfg);
//        String accessKey = QiniuUtils.accessKey;      //AccessKey的值
//        String secretKey = QiniuUtils.secretKey;      //SecretKey的值
//        String bucket = QiniuUtils.bucket;                                          //存储空间名
//        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucketname);
        try {
            Response response = uploadManager.put(FilePath, FileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return QiniuUtils.domain + "/" +FileName;
        }catch (QiniuException ex){
            ex.printStackTrace();
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }

    /**
     * 根据Spring MVC式的MultipartFile类型的图片进行上传的
     * @param file 图片
     * @param filename 保存在七牛云空间的图片名称
     * @return
     * @throws Exception
     */
    public static String updateFile(MultipartFile file, String filename) throws Exception {
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        try {
            InputStream inputStream=file.getInputStream();
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            //buff用于存放循环读取的临时数据
            byte[] buff = new byte[600];
            int rc = 0;
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }

            byte[] uploadBytes  = swapStream.toByteArray();
            try {
                Response response = uploadManager.put(uploadBytes, filename, getUpToken());
                //解析上传成功的结果
                DefaultPutRet putRet;
                putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return QiniuUtils.domain + "/" +putRet.key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                }
            }
        } catch (UnsupportedEncodingException ex) {
        }
        return null;
    }



    @Test
    public void test1() {
        String filepath = "F:\\logo.png";
        String filename = "logo.png";
//        System.out.println(UploadPic(filepath, filename));
    }

    @Test
    public void test2() {
        List<String> strings = new ArrayList<String>();

        strings.add("qqqqq");
        strings.add("wwwww");
        strings.add(null);

        System.out.println(strings.get(1));
        System.out.println(strings.get(2));
    }
}
