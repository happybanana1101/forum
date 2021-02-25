package com.jsq.forum.controller;

import com.jsq.forum.dao.AnswerDao;
import com.jsq.forum.dao.TopicDao;
import com.jsq.forum.model.Answer;
import com.jsq.forum.model.Topic;
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
public class AnswerController {
    @Autowired
    HostHolder hostHolder;
    @Autowired
    AnswerDao answerDao;
    @Autowired
    TopicDao topicDao;


    @RequestMapping(path = "/answers/{id}", method = RequestMethod.GET)
    public String displayAnswersByUser(@PathVariable String id, Model model) {
        List<Answer> answers = answerDao.findAnswerByUser_IdOrderByCreatedDateDesc(Long.parseLong(id));
        User user=hostHolder.getUser();
        model.addAttribute("user",user);
        //model.addAttribute("newMessage", messageDao.countMessageByToId(user.getId()));
        model.addAttribute("answers", answers);
        model.addAttribute("topicDao", topicDao);
        return "answers";
    }

    @RequestMapping(path = "/answers/useful/{id}", method = RequestMethod.GET)
    public String displayUsefulAnswersByUser(@PathVariable String id, Model model) {
        List<Answer> answers = answerDao.findAnswerByUser_IdAndUsefulOrderByCreatedDateDesc(Long.parseLong(id), true);

        User user=hostHolder.getUser();
        model.addAttribute("user",user);
        //model.addAttribute("newMessage", messageDao.countMessageByToId(user.getId()));
        model.addAttribute("answers", answers);
        model.addAttribute("topicDao", topicDao);
        return "answers";
    }
}
