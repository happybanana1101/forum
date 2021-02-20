package com.jsq.forum.service;

import com.jsq.forum.dao.UserDao;
import com.jsq.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class RegisterService {
    @Autowired
    UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    public String reg(String username,String password,String introduction){
        User user = userDao.getUserByUsername(username);
        if (user!=null || password==null || password.equals("")){
            return "/register";
        }
        user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setIntroduction(introduction);
        user.setCreated_date(new Date());
        userDao.addUser(user);
        return "/login";
    }
}
