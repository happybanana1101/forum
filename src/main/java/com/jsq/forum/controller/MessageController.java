package com.jsq.forum.controller;

import com.jsq.forum.dao.AnswerDao;
import com.jsq.forum.dao.MessageDao;
import com.jsq.forum.dao.TopicDao;
import com.jsq.forum.dao.UserDao;
import com.jsq.forum.model.Answer;
import com.jsq.forum.model.Message;
import com.jsq.forum.model.Topic;
import com.jsq.forum.model.User;
import com.jsq.forum.service.MessageService;
import com.jsq.forum.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MessageController {

    @Autowired
    HostHolder hostHolder;
    @Autowired
    MessageDao messageDao;
    @Autowired
    UserDao userDao;
    @Autowired
    TopicDao topicDao;
    @Autowired
    AnswerDao answerDao;
    @Autowired
    MessageService messageService;


    @RequestMapping(path = "/message", method = RequestMethod.GET)
    public String displayMessage(Model model) {
        User user = hostHolder.getUser();
        List<Message> messages = messageDao.selectMessage(user.getId());
        List<Message> unreadmessages = messageDao.selectUnReadMessage(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("newMessage", messageDao.countMessageByToId(user.getId()));
        model.addAttribute("messages", messages);
        model.addAttribute("unReadMessages",unreadmessages);
        model.addAttribute("userDao", userDao);
        model.addAttribute("messageDao", messageDao);
        return  "message";
    }

    @RequestMapping(path = "/topicM/{id}", method = RequestMethod.GET)
    public String displayTopic(@PathVariable String id, Model model) {
        User user = hostHolder.getUser();
        long idUser = user.getId();

        Topic topic = topicDao.findTopicById(Long.valueOf(id));
        List<Answer> answers = answerDao.findAnswerByTopic_Id(Long.valueOf(id));
        model.addAttribute("user", user);
        model.addAttribute("newMessage", messageDao.countMessageByToId(user.getId()));
        model.addAttribute("topic", topic);
        model.addAttribute("answers", answers);
        model.addAttribute("idUser", idUser);
        model.addAttribute("userDao", userDao);

        messageService.deleteMessage(Long.valueOf(id));
        return "topic";
    }
}
