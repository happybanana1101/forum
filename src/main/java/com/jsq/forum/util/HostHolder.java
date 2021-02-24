package com.jsq.forum.util;

import com.jsq.forum.dao.UserDao;
import com.jsq.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;

@Component
public class HostHolder {
    private User user;
    @Autowired
    UserDao userDao;
    public User getUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user=userDao.getUserByUsername(((UserDetails) principal).getUsername());
        return user;
    }
}
