package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("Select * from USERS where username = #{uname}")
    User getUser(String uname);

    @Insert("Insert into users (username, salt, password, firstname, lastname) VALUES" +
            "(#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    int insert(User user);

    @Select("Select userid from users where username = #{username}")
    Integer getUserIdByName(String username);
}
