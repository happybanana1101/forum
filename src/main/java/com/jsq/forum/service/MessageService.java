package com.jsq.forum.service;

import com.jsq.forum.dao.MessageDao;
import com.jsq.forum.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageService {
    @Autowired
    MessageDao messageDao;

    public void addMessage(long from_id, long to_id, String content, long id_topic){
        Message message = new Message();
        message.setFrom_id(from_id);
        message.setTo_id(to_id);
        message.setContent(content);
        message.setCreated_Date(new Date());
        message.setHas_read(0);
        message.setId_topic(id_topic);
        messageDao.addMessage(message);
    }
}
