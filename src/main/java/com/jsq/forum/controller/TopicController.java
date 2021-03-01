package com.jsq.forum.controller;

import com.jsq.forum.dao.AnswerDao;
import com.jsq.forum.dao.TopicDao;
import com.jsq.forum.dao.UserDao;
import com.jsq.forum.model.Answer;
import com.jsq.forum.model.Topic;
import com.jsq.forum.model.User;
import com.jsq.forum.service.RankService;
import com.jsq.forum.service.TopicService;
import com.jsq.forum.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TopicController {
    @Autowired
    HostHolder hostHolder;
    @Autowired
    TopicDao topicDao;
    @Autowired
    AnswerDao answerDao;
    @Autowired
    UserDao userDao;
    @Autowired
    TopicService topicService;
    @Autowired
    RankService rankService;

    @RequestMapping(path = "/topic/{id}", method = RequestMethod.POST)
    public View updateAnswer(@RequestParam String id_topic, @RequestParam String action, @RequestParam String id_answer,
                             @RequestParam(required = false) String state, @RequestParam String username,HttpServletRequest request) {
        switch (action){
            case "useful":
                answerDao.setUsefulForAnswer(!Boolean.valueOf(state),Long.parseLong(id_answer));
                rankService.changepoint(username,"setuseful",!Boolean.valueOf(state));
                break;
            case "delete":
                answerDao.deleteAnswerById(Long.parseLong(id_answer));
                rankService.changepoint(username,"deleteAnswer",!Boolean.valueOf(state));
                break;
        }

        String context = request.getContextPath();
        return new RedirectView(context+"/topic/"+id_topic);
    }
    @RequestMapping(path = "/topic/{id}", method = RequestMethod.GET)
    public String displayTopic(@PathVariable String id, Model model) {
        User user = hostHolder.getUser();
        long idUser = user.getId();

        Topic topic = topicDao.findTopicById(Long.valueOf(id));
        List<Answer> answers = answerDao.findAnswerByTopic_Id(Long.valueOf(id));


        model.addAttribute("user", user);
        //model.addAttribute("newMessage", messageDao.countMessageByToId(user.getId()));
        model.addAttribute("topic", topic);
        model.addAttribute("answers", answers);
        model.addAttribute("idUser", idUser);
        model.addAttribute("userDao", userDao);

        return "topic";
    }

    @RequestMapping(path = "/topic", method = RequestMethod.POST)
    public View addAnswer(@RequestParam("content") String content, @RequestParam("code") String code,
                          @RequestParam("id_topic") String id_topic, @RequestParam("id_user") String id_user,
                          HttpServletRequest request) {

        topicService.addAnswer(content, code, id_topic, id_user);
        String contextPath = request.getContextPath();
        return new RedirectView(contextPath + "/topic/" + id_topic);
    }
}
