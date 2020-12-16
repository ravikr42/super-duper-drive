package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {

    @Select("select * from credentials where userid = #{id}")
    List<Credential> getCredentialsByUserId(Integer id);

    @Insert("insert into credentials (url, username, keya, password, userid) VALUES (#{url}, #{userName}, #{keya}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Update("update credentials set url = #{url}, username=#{userName}, keya=#{keya}, password=#{password} where credentialId=#{credentialId}")
    int updateCredential(Credential credential);

    @Delete("delete from credentials where credentialId=#{credentialId}")
    int deleteCredential(Integer credentialId);
}
