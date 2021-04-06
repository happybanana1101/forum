package com.jsq.forum.dao;

import com.jsq.forum.model.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageDao {
    void addMessage(Message message);
    List<Message> selectMessage(long id);
    List<Message> selectUnReadMessage(long id);
    int countMessageByToId(long id);
}
