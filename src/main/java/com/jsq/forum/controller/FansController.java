package com.jsq.forum.controller;

import com.jsq.forum.dao.MessageDao;
import com.jsq.forum.model.Fans;
import com.jsq.forum.model.User;
import com.jsq.forum.service.FansService;
import com.jsq.forum.service.FollowService;
import com.jsq.forum.util.HostHolder;
import com.jsq.forum.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class FansController {

    @Autowired
    FollowService followService;
    @Autowired
    JedisUtil jedisUtil;
    @Autowired
    FansService fansService;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    MessageDao messageDao;


    @RequestMapping(path="/follow/{userId}_{otherUserId}",method= RequestMethod.POST)
    public View addFollow(@PathVariable Long userId, @PathVariable Long otherUserId, HttpServletRequest request) {
        followService.addFollow(userId,otherUserId);
        String contextPath = request.getContextPath();
        return new RedirectView(contextPath + "/profile/" + otherUserId);
    }

    @RequestMapping(path="/unfollow/{userId}_{otherUserId}",method= RequestMethod.POST)
    public View deleteFollow(@PathVariable Long userId, @PathVariable Long otherUserId,HttpServletRequest request) {
        followService.deleteFollow(userId,otherUserId);
        String contextPath = request.getContextPath();
        return new RedirectView(contextPath + "/profile/" + otherUserId);
    }

    @RequestMapping(path = "/fans/{id}", method = RequestMethod.GET)
    public String displayFans(@PathVariable String id, Model model){
        List<Fans> fans = fansService.getFans(id);
        User user = hostHolder.getUser();
        model.addAttribute("fans",fans);
        model.addAttribute("user",user);
        model.addAttribute("newMessage", messageDao.countMessageByToId(user.getId()));
        return "fans";
    }

}
