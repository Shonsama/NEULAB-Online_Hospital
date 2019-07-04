package com.neuedu.fastdfs.model.service;

import com.neuedu.fastdfs.model.mapper.PictureMapper;
import com.neuedu.fastdfs.model.po.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PictureService {
    private final PictureMapper dao;

    @Autowired
    public PictureService(PictureMapper dao) {
        this.dao = dao;
    }

    public void insert(Picture picture) {
        dao.insert(picture);
    }

    public List<Picture> getUrl(String id){
        return dao.getUrl(id);
    }
}
