package com.jsq.forum.dao;

import com.jsq.forum.model.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TopicDao {
    List<Topic> findAll();
    List<Topic> findTopicsByCategoryOrderByCreatedDateDesc(@Param("category") String category);
    Topic findTopicById(@Param("id") Long id);
}
