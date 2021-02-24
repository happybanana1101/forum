package com.jsq.forum.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jsq.forum.dao.TopicDao;
import com.jsq.forum.model.PageBean;
import com.jsq.forum.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {
    @Autowired
    private TopicDao topicDao;
    public PageBean<Topic> findItemByPage(String category,int currentPage,int pageSize){
        int countNum = 0;
        PageHelper.startPage(currentPage,pageSize);
        List<Topic> allTopic = null;
        if(category.equals("all")){
            allTopic = topicDao.findAll();
        }else {
            allTopic = topicDao.findTopicsByCategoryOrderByCreatedDateDesc(category);
        }
        countNum = allTopic==null?0:allTopic.size();
        PageBean<Topic> pageData = new PageBean<>(currentPage,pageSize,countNum);
        pageData.setItems(allTopic);
        return pageData;
    }
}
