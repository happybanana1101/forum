package com.jsq.forum.controller;

import com.jsq.forum.dao.AnswerDao;
import com.jsq.forum.dao.MessageDao;
import com.jsq.forum.dao.UserDao;
import com.jsq.forum.model.PageBean;
import com.jsq.forum.model.Topic;
import com.jsq.forum.model.User;
import com.jsq.forum.service.PageService;
import com.jsq.forum.service.TopicService;
import com.jsq.forum.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TopicsController {
    @Autowired
    PageService pageService;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    AnswerDao answerDao;
    @Autowired
    UserDao userDao;
    @Autowired
    TopicService topicService;
    @Autowired
    MessageDao messageDao;


    @RequestMapping(path="/topics/{category}/{currentPage}", method= RequestMethod.GET)
    public String displayTopicPage(@PathVariable String category, @PathVariable int currentPage, Model model) {
        PageBean<Topic> pageTopic = pageService.findItemByPage(category,currentPage,10);
        List<Topic> pageList = pageTopic.getItems();
        String header = setHeader(category);
        int topicsTotalNum=topicService.getTopicsByCategory(category).size();
        User user = hostHolder.getUser();
        model.addAttribute("user", user);
        model.addAttribute("topics",pageList);
        model.addAttribute("header", header);
        model.addAttribute("answerDao", answerDao);
        model.addAttribute("userDao", userDao);
        model.addAttribute("currentPage", pageTopic.getCurrentPage());
        model.addAttribute("totalPage", pageTopic.getTotalPage());
        model.addAttribute("hasNext", pageTopic.getIsMore());
        model.addAttribute("isUserTopicPage", false);
        model.addAttribute("topicsTotalNum", topicsTotalNum);
        model.addAttribute("newMessage", messageDao.countMessageByToId(user.getId()));
        return "topics";
    }

    @RequestMapping(path = "/topics/user/{id}_{currentPage}", method = RequestMethod.GET)
    public String displayTopicsByUser(@PathVariable String id, @PathVariable int currentPage, Model model) {
//		List<Topic> topics = topicsService.getTopicsByUser(id);
        PageBean<Topic> pageTopic=pageService.findItemByUser(id, currentPage, 10);
        List<Topic> topics=pageTopic.getItems();
        int topicsTotalNum=topicService.getTopicsByUser(id).size();
        String header = setHeader("user");

        User user=hostHolder.getUser();
        model.addAttribute("user", user);
        model.addAttribute("newMessage", messageDao.countMessageByToId(user.getId()));
        model.addAttribute("topics", topics);
        model.addAttribute("header", header);
        model.addAttribute("answerDao", answerDao);
        model.addAttribute("userDao", userDao);
        model.addAttribute("currentPage", pageTopic.getCurrentPage());
        model.addAttribute("totalPage", pageTopic.getTotalPage());
        model.addAttribute("hasNext", pageTopic.getIsMore());
        model.addAttribute("topicsTotalNum", topicsTotalNum);
        model.addAttribute("isUserTopicPage", true);
        return "topics";
    }

    private String setHeader(String category) {
        switch (category) {
            case "se":
                return "Java Standard Edition";
            case "ee":
                return "Java Enterprise Edition";
            case "mbs":
                return "MyBatis";
            case "spring":
                return "Spring Framework";
            case "web":
                return "HTML/CSS/JavaScript";
            case "other":
                return "其他";
            case "all":
                return "All topics";
            default:
                return "User's topics";
        }
    }
}
