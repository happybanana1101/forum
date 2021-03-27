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
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    RankService rankService;
    @Transactional
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
        User user = hostHolder.getUser();
        rankService.changepoint(user.getUsername(),"addAnswer");
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
