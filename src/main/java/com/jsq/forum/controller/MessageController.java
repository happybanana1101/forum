package com.jsq.forum.controller;

import com.jsq.forum.dao.MessageDao;
import com.jsq.forum.dao.UserDao;
import com.jsq.forum.model.Answer;
import com.jsq.forum.model.Message;
import com.jsq.forum.model.User;
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
}
