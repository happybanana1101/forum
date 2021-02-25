package com.jsq.forum.dao;

import com.jsq.forum.model.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnswerDao {
    Long countAnswersByTopic_Id(@Param("idTopic") Long idTopic);
    List<Answer> findAnswerByTopic_Id(@Param("idTopic") Long idTopic);
    int addAnswer(Answer answer);
    Long countAnswersByUser_Id(Long id);
    Long countAnswersByUser_IdAndUseful(@Param("IdUser") Long IdUser, @Param("useful") boolean useful);
    List<Answer> findAnswerByUser_IdOrderByCreatedDateDesc(Long id);
    List<Answer> findAnswerByUser_IdAndUsefulOrderByCreatedDateDesc(@Param("id") Long id,@Param("useful")Boolean useful);
    void setUsefulForAnswer(@Param("state")Boolean state, @Param("answerId")Long answerID);
    void deleteAnswerById(Long answerid);
}
