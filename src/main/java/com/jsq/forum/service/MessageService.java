package com.jsq.forum.service;

import com.jsq.forum.dao.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    MessageDao messageDao;

    public void addMessage(){

    }
}
