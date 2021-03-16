package com.jsq.forum.controller;

import com.jsq.forum.dao.PointsDao;
import com.jsq.forum.dao.UserDao;
import com.jsq.forum.model.User;
import com.jsq.forum.service.RankService;
import com.jsq.forum.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;


@Controller
public class RankController {
    @Autowired
    HostHolder hostHolder;
    @Autowired
    RankService rankService;
    @Autowired
    PointsDao pointsDao;
    @Autowired
    UserDao userDao;

    @RequestMapping(path="/rank",method= RequestMethod.GET)
    public String rank(Model model) {
        User user=hostHolder.getUser();
        Double points = rankService.getPoint(user.getUsername());
        Set<String> pointSet=rankService.getPointSet();
        model.addAttribute("user", user);
        //model.addAttribute("newMessage", messageDao.countMessageByToId(user.getId()));
        model.addAttribute("pointSet", pointSet);
        model.addAttribute("userDao", userDao);
        model.addAttribute("pointsDao", pointsDao);
        return "rank";
    }


}
