package com.ict.quiz.api.user;

import com.ict.quiz.domain.User;
import com.ict.quiz.domain.api.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
            value = "사용자 로그인"
            , notes = "사용자를 조회하여 정보를 리턴한다."
            , response = User.class
    )
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginReqDto reqDto) throws Exception {
        User user;
        try {
            user = userApiService.findUser(reqDto);
        } catch (Exception e) {
            ErrorResult errorResult = null;
            if("NONE".equals(e.getMessage())) {
                errorResult = new ErrorResult("1", "해당 ID의 사용자가 없습니다.");
            }
            if("PW".equals(e.getMessage())) {
                errorResult = new ErrorResult("2", "비밀번호가 일치하지 않습니다.");
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
        log.info("[GET] /api/u/his called, user_id={}", user_id);
        List<UserQuestionHisResDto> userQuestionHisResDto = userApiService.findHisList(user_id);

        return ResponseEntity.ok(userQuestionHisResDto);
    }

    @ApiOperation(
            value = "사용자 문제 이력 상세 조회"
            , notes = "사용자 문제 셋 ID로 문제 풀이 이력 상세를 조회한다."
            , response = UserQuestionHisDetailResDto.class
    )
    @GetMapping("/his/{id}")
    public ResponseEntity hisDetail(@PathVariable("id") Long id) {

        UserQuestionHisDetailResDto hisDetail = userApiService.findHisDetail(id);

        return ResponseEntity.ok(hisDetail);
    }

    @GetMapping("/his/categories")
    public ResponseEntity categories(Long user_id) {
        log.info("[GET] /api/u/his/categories called, user_id={}", user_id);
        List<CategoryStatsCategoryResDto> categoryResDto = userApiService.findCategoryHis(user_id);
        return ResponseEntity.ok(categoryResDto);
    }


    @ApiOperation(
            value = "사용자 카테고리별 문제 현황 조회"
            , notes = "사용자 ID와 카테고리 ID로 해당 카테고리의 문제별 현황을 조회한다."
            , response = CategoryStatsResDto.class
    )
    @GetMapping("/category-stats")
    public ResponseEntity getCategoryStats(Long user_id, Long category_id) {

        List<CategoryStatsResDto> categoryStats = userApiService.findCategoryStats(user_id, category_id);
        return ResponseEntity.ok(categoryStats);
    }

    @PostMapping("/api/u/session-extend")
    public ResponseEntity extendSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 세션 연장 (새로운 타임아웃 설정)
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(401).build();
    }
}
