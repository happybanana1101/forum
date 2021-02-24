package com.jsq.forum.dao;

import com.jsq.forum.model.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnswerDao {
    Long countAnswersByTopic_Id(@Param("idTopic") Long idTopic);
    List<Answer> findAnswerByTopic_Id(@Param("idTopic") Long idTopic);
}
