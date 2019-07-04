package com.neuedu.fastdfs.controller;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.fastdfs.fastDFS.FastDFSFile;
import com.neuedu.fastdfs.fastDFS.FileManager;
import com.neuedu.fastdfs.model.po.Picture;
import com.neuedu.fastdfs.model.service.PictureService;
import org.csource.common.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.swing.FilePane;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/dfs")
public class Controller {
    @Autowired
    private PictureService pictureService;


    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public JSONObject uplaod(@PathVariable("id") String id, @RequestParam("file") MultipartFile file) {//1. 接受上传的文件  @RequestParam("file") MultipartFile file
        //编写图片上传的业务逻辑方法
        Picture picture = new Picture();
        JSONObject result = new JSONObject();
        //获取图片名称
        String filename = file.getOriginalFilename();
        //获取图片扩展名
        String ext = filename.substring(filename.lastIndexOf(".") + 1);
        //生成图片名称
        String imgName = id;//自己写的一个获取字符串的方法作为图片名称
        picture.setId(id);

        //图片上传
        JSONObject jo = new JSONObject();
        try {
            InputStream in = file.getInputStream();
            byte[] bytes = new byte[10000000];//10M
            int len;
            while ( (len = in.read(bytes)) != -1);
            in.close();
            FastDFSFile fastDFSFile = new FastDFSFile(bytes,ext);
            NameValuePair[] meta_list = new NameValuePair[4];
            meta_list[0] = new NameValuePair("fileName", imgName);
            meta_list[1] = new NameValuePair("fileLength", String.valueOf(len));
            meta_list[2] = new NameValuePair("fileExt", ext);
            meta_list[3] = new NameValuePair("fileAuthor", "WangLiang");
            String filePath = FileManager.upload(fastDFSFile,meta_list);
            System.out.println(filePath);
            picture.setUrl(filePath);
            jo.put("url",filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            pictureService.insert(picture);
        }catch (RuntimeException e){
            e.printStackTrace();
            result.put("code","500");
            result.put("data",jo);
        }

        result.put("code","200");
        result.put("data",jo);
        return result;
    }

    @RequestMapping("/get")
    public JSONObject getUrl(@RequestBody JSONObject request){
        JSONObject result = new JSONObject();
        String id = request.getString("id");
        if(id == null){
            result.put("code","500");
            result.put("msg","无信息");
            result.put("data",null);
            return result;
        }

        List<Picture> pictures = pictureService.getUrl(id);
        result.put("code","200");
        result.put("data",pictures);
        return result;
    }

}
