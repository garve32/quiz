// ExplanationApiService.java
package com.ict.quiz.api.user;

import com.ict.quiz.domain.api.QuestionExplanationDto;
import com.ict.quiz.domain.api.ExplanationImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExplanationApiService {

    private final ExplanationMapper explanationMapper;

    /**
     * 특정 문제의 해설 조회 (이미지 포함)
     */
    public QuestionExplanationDto getByQuestionId(Long questionId) {
        QuestionExplanationDto explanation = explanationMapper.findByQuestionId(questionId);
        if (explanation != null) {
            List<ExplanationImageDto> images = explanationMapper.findImagesByExplanationId(explanation.getId());
            explanation.setImages(images);
        }
        return explanation;
    }

    /**
     * 문제 이력의 모든 문제에 대한 해설 조회 (성능 최적화)
     * question_set을 파싱해서 한번에 조회
     */
    public List<QuestionExplanationDto> getExplanationsByQuestionSet(String questionSet) {
        if (questionSet == null || questionSet.trim().isEmpty()) {
            return List.of();
        }

        // "1,7,9,11,13,15" 형태를 파싱
        List<Long> questionIds = Arrays.stream(questionSet.split(","))
                .map(String::trim)
                .map(Long::valueOf)
                .collect(Collectors.toList());

        List<QuestionExplanationDto> explanations = explanationMapper.findByQuestionIds(questionIds);

        // 각 해설에 이미지 정보 추가
        for (QuestionExplanationDto explanation : explanations) {
            List<ExplanationImageDto> images = explanationMapper.findImagesByExplanationId(explanation.getId());
            explanation.setImages(images);
        }

        return explanations;
    }
}