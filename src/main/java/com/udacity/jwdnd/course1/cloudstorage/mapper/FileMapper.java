package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.FileDoc;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("select * from files where userid = #{userid}")
    List<FileDoc> getFileByUserId(Integer userid);

    @Insert("insert into files (filename, contenttype, filesize, userid, filedata) " +
            "Values (#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(FileDoc file);

    @Delete("delete from files where fileId = #{fileId}")
    int deleteFile(Integer fileId);

    @Select("select * from files where filename = #{filename} AND userId=#{userId}")
    FileDoc findDuplicateFile(String filename, Integer userId);

    @Select("select * from files where fileid = #{id}")
    FileDoc getFile(Integer id);


}
