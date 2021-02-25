package com.jsq.forum.service;

import com.jsq.forum.dao.AnswerDao;
import com.jsq.forum.dao.TopicDao;
import com.jsq.forum.dao.UserDao;
import com.jsq.forum.model.Answer;
import com.jsq.forum.model.Topic;
import com.jsq.forum.model.User;
import com.jsq.forum.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Holder;
import java.util.Date;
import java.util.List;

@Service
public class TopicService {
    @Autowired
    TopicDao topicDao;
    @Autowired
    UserDao userDao;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    AnswerDao answerDao;

    public void addAnswer(String content,String code,String id_topic,String id_user){
        Answer answer = new Answer();
        answer.setContent(content);
        if("".equals(code)) answer.setCode(null);
        else answer.setCode(code);
        answer.setIdTopic(Integer.parseInt(id_topic));
        answer.setCreatedDate(new Date());
        answer.setIdUser(Integer.parseInt(id_user));
        answer.setTopic(topicDao.findTopicById(Long.parseLong(id_topic)));
        answer.setUser(userDao.getUserById(Long.parseLong(id_user)));
        answer.setUseful(false);
        answerDao.addAnswer(answer);
        // 触发评论的异步队列
        User user = hostHolder.getUser();
        // 如果评论自己的话题不会触发站内信通知
//        if (user.getId() != topicDao.getId_userById(Long.parseLong(id_topic))) {
//            EventModel eventModel = new EventModel(EventType.COMMENT);
//            eventModel.setCreatedDate(new Date());
//            eventModel.setActorId(Integer.parseInt(String.valueOf(user.getId())));
//            eventModel.setEntityId(Integer.valueOf(id_topic));
//            eventModel.setEntityType(EntityType.ENTITY_COMMENT);
//            eventModel.setEntityOwnerId(topicDao.getId_userById(Long.parseLong(id_topic)));
//            eventProducer.fireEvent(eventModel);
//        }
    }

    public List<Topic> getTopicsByUser(String userId){
        return topicDao.findTopicsByUser_IdOrderByCreatedDateDesc(Long.valueOf(userId));
    }

    public List<Topic> getTopicsByCategory(String category){
        if (category.equals("all")) {
            return topicDao.findAll();
        } else {
            return topicDao.findTopicsByCategoryOrderByCreatedDateDesc(category);
        }
    }
}
