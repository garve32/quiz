package com.ict.quiz.user.mapper;

import com.ict.quiz.user.vo.QuestionVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    QuestionVo getQuestionById();
}
