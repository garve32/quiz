// ExplanationMapper.java
package com.ict.quiz.api.user;

import com.ict.quiz.domain.QuestionExplanation;
import com.ict.quiz.domain.ExplanationImage;
import com.ict.quiz.domain.api.ExplanationImageDto;
import com.ict.quiz.domain.api.QuestionExplanationDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ExplanationMapper {

    // 특정 문제의 해설 조회
    QuestionExplanationDto findByQuestionId(@Param("question_id") Long questionId);

    // 해설 이미지들 조회
    List<ExplanationImageDto> findImagesByExplanationId(@Param("explanation_id") Long explanationId);

    // 여러 문제의 해설을 한번에 조회 (성능 최적화)
    List<QuestionExplanationDto> findByQuestionIds(@Param("question_ids") List<Long> questionIds);

    // 해설 생성 (관리자용)
    void insertExplanation(QuestionExplanation explanation);

    // 해설 이미지 생성
    void insertExplanationImage(ExplanationImage image);

    // 해설 수정
    void updateExplanation(QuestionExplanation explanation);

    // 해설 삭제 (soft delete)
    void deleteExplanation(@Param("id") Long id);

    // 이미지 삭제 (soft delete)
    void deleteExplanationImage(@Param("id") Long id);
}