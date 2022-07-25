package com.ict.quiz.api.user;

import com.ict.quiz.domain.api.UserQuestionHisResDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/u")
public class UserApiController {

    private final UserApiService userApiService;

    @ApiOperation(
            value = "사용자 문제 이력 리스트 조회"
            , notes = "사용자 ID로 문제 풀이 이력을 조회한다."
            , response = UserQuestionHisResDto.class
            , responseContainer = "List"
    )
    @GetMapping("/his")
    public ResponseEntity his(Long user_id) {

        List<UserQuestionHisResDto> userQuestionHisResDto = userApiService.findHisList(user_id);

        return ResponseEntity.ok(userQuestionHisResDto);
    }
}
