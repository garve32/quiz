// ExplanationApiController.java
package com.ict.quiz.api.user;

import com.ict.quiz.domain.api.QuestionExplanationDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "문제 해설 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExplanationApiController {

    private final ExplanationApiService explanationApiService;

    @ApiOperation(value = "특정 문제의 해설 조회", notes = "문제 ID로 해설 정보를 조회합니다.")
    @GetMapping("/explanation/{questionId}")
    public ResponseEntity<QuestionExplanationDto> getExplanation(
            @ApiParam(value = "문제 ID", required = true, example = "9")
            @PathVariable Long questionId) {

        QuestionExplanationDto explanation = explanationApiService.getByQuestionId(questionId);

        if (explanation == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(explanation);
    }

    @ApiOperation(value = "여러 문제의 해설 조회", notes = "문제 세트로 여러 해설을 한번에 조회합니다.")
    @GetMapping("/explanations")
    public ResponseEntity<List<QuestionExplanationDto>> getExplanations(
            @ApiParam(value = "문제 세트 (콤마로 구분된 문제 ID)", required = true, example = "1,7,9,11,13,15")
            @RequestParam String questionSet) {

        List<QuestionExplanationDto> explanations = explanationApiService.getExplanationsByQuestionSet(questionSet);

        return ResponseEntity.ok(explanations);
    }
}