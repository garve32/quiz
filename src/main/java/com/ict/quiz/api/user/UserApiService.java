package com.ict.quiz.api.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ict.quiz.domain.User;
import com.ict.quiz.domain.UserQuestion;
import com.ict.quiz.domain.api.UserAddReqDto;
import com.ict.quiz.domain.api.UserQuestionHisResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserApiService {

    private final UserApiMapper userApiMapper;
    public List<UserQuestionHisResDto> findHisList(Long user_id) {

        List<UserQuestion> hisList = userApiMapper.findHisList(user_id);

        List<UserQuestionHisResDto> resList = hisList.stream().map(o -> new UserQuestionHisResDto(o.getId(), o.getUser_id(), o.getSeq(), o.getCategory_id()
                        , o.getQuestion_set(), o.getProgress_set(), o.getAnswer_set(), o.getCorrect_set()
                        , o.getStart_dt(), o.getEnd_dt(), o.getQuestion_set().split(",").length, (int) Arrays.stream(o.getCorrect_set().split(",")).filter(c -> c.equals("1")).count()))
                .collect(Collectors.toList());

//        resList.forEach(h -> {
//            h.setQuestion_cnt(h.getQuestion_set().split(",").length);
//            h.setCorrect_cnt((int) Arrays.stream(h.getCorrect_set().split(",")).filter(c -> c.equals("1")).count());
//        });

        return resList;
    }

    public User insertUser(UserAddReqDto reqDto) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.convertValue(reqDto, User.class);
        // 중복 확인
        int cnt = userApiMapper.checkDup(user);
        if(cnt > 0) {
            throw new Exception("DUP");
        }

        // 생성
        userApiMapper.insertUser(user);

        return user;
    }
}
