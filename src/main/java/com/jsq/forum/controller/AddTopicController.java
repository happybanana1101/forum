package com.jsq.forum.controller;

import com.jsq.forum.dao.MessageDao;
import com.jsq.forum.dao.TopicDao;
import com.jsq.forum.dao.UserDao;
import com.jsq.forum.model.Message;
import com.jsq.forum.model.Topic;
import com.jsq.forum.model.User;
import com.jsq.forum.service.RankService;
import com.jsq.forum.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class AddTopicController {
    @Autowired
    HostHolder hostHolder;
    @Autowired
    UserDao userDao;
    @Autowired
    TopicDao topicDao;
    @Autowired
    RankService rankService;
    @Autowired
    MessageDao messageDao;

    @RequestMapping(path = "/addTopic", method = RequestMethod.GET)
    public String displayMyProfile(Model model) {
        User user = hostHolder.getUser();
        //Long points = userDao.getPoints(user.getId());
        List<Message> messages = messageDao.selectMessage(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("messages",messages);
        return "addTopic";
    }
    @RequestMapping(path = "/addTopic", method = RequestMethod.POST)
    public View addTask(@RequestParam("category") String category, @RequestParam("title") String title,
                        @RequestParam("content") String content, @RequestParam("code") String code,
                        @RequestParam("id_user") String id_user, HttpServletRequest request) {
        User user = hostHolder.getUser();
        Topic topic = new Topic();
        topic.setCategory(category);
        topic.setTitle(title);
        topic.setContent(content);
        if("".equals(code)) topic.setCode(null);
        else topic.setCode(code);
        topic.setCreatedDate(new Date());
        topic.setIdUser(Integer.parseInt(id_user));
        topic.setUser(user);
        topicDao.addTopic(topic);
        rankService.changepoint(user.getUsername(),"addtopic");
        String contextPath = request.getContextPath();
        return new RedirectView(contextPath+"/topics/"+category+"/1");
    }
}
