package com.neuedu.fastdfs.model.mapper;

import com.neuedu.fastdfs.model.po.Picture;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface PictureMapper {
    @Select("SELECT * FROM pic WHERE id=#{id}")
    List<Picture> getUrl(String id);

    @Insert("INSERT INTO pic (id, url) VALUES(#{id}, #{url})")
    void insert(Picture picture);

}
