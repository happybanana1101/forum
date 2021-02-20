package com.jsq.forum.dao;

import com.jsq.forum.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    int addUser(User user);
    User getUserByUsername(@Param("username") String username);
}
