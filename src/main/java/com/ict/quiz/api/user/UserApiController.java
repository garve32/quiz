package com.ict.quiz.api.user;

import com.ict.quiz.domain.User;
import com.ict.quiz.domain.api.ErrorResult;
import com.ict.quiz.domain.api.UserAddReqDto;
import com.ict.quiz.domain.api.UserQuestionHisResDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/u")
public class UserApiController {

    private final UserApiService userApiService;

    @ApiOperation(
            value = "사용자 등록"
            , notes = "사용자를 신규 등록한다."
            , response = User.class
    )
    @PostMapping("")
    public ResponseEntity addUser(@RequestBody UserAddReqDto reqDto) throws Exception {
        User user;
        try {
            user = userApiService.insertUser(reqDto);
        } catch (Exception e) {
            ErrorResult errorResult = null;
            if("DUP".equals(e.getMessage())) {
                errorResult = new ErrorResult("1", "중복된 사용자가 있습니다.");
            }
            return ResponseEntity.badRequest().body(errorResult);
        }

        return ResponseEntity.ok(user);
    }

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
