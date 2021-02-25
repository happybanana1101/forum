package com.jsq.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class FooterController {
    @RequestMapping(path = "/aboutUs")
    public String aboutUs() {
        return "aboutUs";
    }

    @RequestMapping(path = "/title")
    public String title() {
        return "title";
    }
}
