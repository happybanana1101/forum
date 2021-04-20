package com.jsq.forum.controller;

import com.jsq.forum.dao.AnswerDao;
import com.jsq.forum.dao.MessageDao;
import com.jsq.forum.dao.TopicDao;
import com.jsq.forum.dao.UserDao;
import com.jsq.forum.model.Answer;
import com.jsq.forum.model.User;
import com.jsq.forum.service.FollowService;
import com.jsq.forum.service.RankService;
import com.jsq.forum.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    HostHolder hostHolder;
    @Autowired
    UserDao userDao;
    @Autowired
    TopicDao topicDao;
    @Autowired
    AnswerDao answerDao;
    @Autowired
    FollowService followService;
    @Autowired
    RankService rankService;
    @Autowired
    MessageDao messageDao;

    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public String displayMyProfile(Model model) {
        User user = hostHolder.getUser();
        Double point = rankService.getPoint(user.getUsername());
        Long numberOfTopics = topicDao.countTopicsByUser_Id(user.getId());
        Long numberOfAnswers = answerDao.countAnswersByUser_Id(user.getId());
        Long numberOfHelped = answerDao.countAnswersByUser_IdAndUseful(user.getId(), true);

        model.addAttribute("user", user);
        model.addAttribute("newMessage", messageDao.countMessageByToId(user.getId()));
        model.addAttribute("points", point);
        model.addAttribute("numberOfTopics", numberOfTopics);
        model.addAttribute("numberOfAnswers", numberOfAnswers);
        model.addAttribute("numberOfHelped", numberOfHelped);
        model.addAttribute("switch", true);
        return "profile";
    }

    @RequestMapping(path = "/profile/{id}", method = RequestMethod.GET)
    public String displayProfileById(@PathVariable Long id, Model model) {
        User user = userDao.getUserById(id);
        Double point = rankService.getPoint(user.getUsername());
        Long numberOfTopics = topicDao.countTopicsByUser_Id(id);
        Long numberOfAnswers = answerDao.countAnswersByUser_Id(id);
        Long numberOfHelped = answerDao.countAnswersByUser_IdAndUseful(id, true);
        User otherUser=hostHolder.getUser();
        boolean isFollowed = followService.isFollow(otherUser.getId(), id);

        model.addAttribute("user", otherUser);
        model.addAttribute("otherUser", user);
        model.addAttribute("newMessage", messageDao.countMessageByToId(hostHolder.getUser().getId()));
        model.addAttribute("points", point);
        model.addAttribute("numberOfTopics", numberOfTopics);
        model.addAttribute("numberOfAnswers", numberOfAnswers);
        model.addAttribute("numberOfHelped", numberOfHelped);
        model.addAttribute("switch", (user.getId()==otherUser.getId())?true:false);
        model.addAttribute("followNums", followService.getFollowNum(user.getId()));
        model.addAttribute("commonFansNum", followService.getCommonFans(user.getId(), otherUser.getId()).size());
        model.addAttribute("isFollowed", isFollowed);
        return "profile";
    }



}
