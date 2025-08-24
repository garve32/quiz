package com.ict.quiz.api.user;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.ict.quiz.domain.api.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.QuestionOption;
import com.ict.quiz.domain.QuestionOptionResult;
import com.ict.quiz.domain.QuestionResult;
import com.ict.quiz.domain.QuestionResultDetail;
import com.ict.quiz.domain.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserApiService {

    private final UserApiMapper userApiMapper;
    private final QuestionApiMapper questionApiMapper;
    public List<UserQuestionHisResDto> findHisList(Long user_id) {

        List<UserQuestionHisResDto> hisList = userApiMapper.findHisList(user_id);

//        List<UserQuestionHisResDto> resList = hisList.stream().map(o -> new UserQuestionHisResDto(o.getId(), o.getUser_id(), o.getSeq(), o.getCategory_id()
//                        , o.getQuestion_set(), o.getProgress_set(), o.getAnswer_set(), o.getCorrect_set()
//                        , o.getStart_dt(), o.getEnd_dt(), o.getQuestion_set().split(",").length, (int) Arrays.stream(o.getCorrect_set().split(",")).filter(c -> c.equals("1")).count()))
//                .collect(Collectors.toList());

//        resList.forEach(h -> {
//            h.setQuestion_cnt(h.getQuestion_set().split(",").length);
//            h.setCorrect_cnt((int) Arrays.stream(h.getCorrect_set().split(",")).filter(c -> c.equals("1")).count());
//        });

        return hisList;
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
        user.setPassword(null);

        return user;
    }

    public User findUser(UserLoginReqDto reqDto) throws Exception {
        User user = userApiMapper.findUser(reqDto.getLogin_id());
        if(user == null) {
            throw new Exception("NONE");
        }
        if(!user.getPassword().equals(reqDto.getPassword())) {
            throw new Exception("PW");
        }

        user.setPassword(null);
        return user;
    }

    public UserQuestionHisDetailResDto findHisDetail(Long id) {
        // 메인 조회
        HisDetailDto his = userApiMapper.findHisDetail(id);

        List<QuestionResultDetail> questionResultDetails = new LinkedList<>();

        String[] q_set = his.getQuestion_set().split(",");
        String[] a_set = his.getAnswer_set().split(",");
        String[] c_set = his.getCorrect_set().split(",");

        // 1) 질문 ID 목록 파싱 후 일괄 조회
        List<Long> questionIds = new LinkedList<>();
        for (String qidStr : q_set) {
            questionIds.add(Long.valueOf(qidStr));
        }

        List<Question> questions = questionApiMapper.findByIds(questionIds);
        // 질문을 ID→Question 맵으로 구성
        java.util.Map<Long, Question> questionMap = new java.util.HashMap<>();
        for (Question q : questions) {
            questionMap.put(q.getId(), q);
        }

        // 2) 선택지 일괄 조회 후 question_id → options 묶기
        List<QuestionOption> allOptions = questionApiMapper.findOptionsByQuestionIds(questionIds);
        java.util.Map<Long, List<QuestionOption>> optionsByQuestionId = new java.util.HashMap<>();
        for (QuestionOption opt : allOptions) {
            optionsByQuestionId.computeIfAbsent(opt.getQuestion_id(), k -> new LinkedList<>()).add(opt);
        }

        ObjectMapper om = new ObjectMapper();
        for (int i = 0; i < q_set.length; i++) {
            long qId = Long.parseLong(q_set[i]);
            Question question = questionMap.get(qId);
            if (question == null) {
                continue;
            }

            QuestionResultDetail questionResultDetail = new QuestionResultDetail();
            QuestionResult questionResult = om.convertValue(question, QuestionResult.class);
            questionResult.setCorrect_yn("1".equals(c_set[i]) ? "Y" : "N");
            if (question.getImage() != null && question.getImage().length > 0) {
                String image = Base64.encodeBase64String(question.getImage());
                questionResult.setEncoded_image(image);
            }
            questionResultDetail.setQuestion(questionResult);

            List<QuestionOption> options = optionsByQuestionId.getOrDefault(qId, java.util.Collections.emptyList());
            List<QuestionOptionResult> questionOptionResults = new LinkedList<>();
            String[] selectedOptionIds = a_set[i].split(":");
            for (QuestionOption option : options) {
                QuestionOptionResult questionOptionResult = om.convertValue(option, QuestionOptionResult.class);
                boolean selected = Arrays.stream(selectedOptionIds).anyMatch(a -> String.valueOf(option.getId()).equals(a));
                questionOptionResult.setSelect_yn(selected ? "Y" : "N");
                questionOptionResults.add(questionOptionResult);
            }
            questionResultDetail.setOptions(questionOptionResults);
            questionResultDetails.add(questionResultDetail);
        }

        UserQuestionHisDetailResDto result = new UserQuestionHisDetailResDto();
        result.setId(his.getId());
        result.setCategory_id(his.getCategory_id());
        result.setCategory_nm(his.getCategory_nm());
        result.setSuccess_cd(his.getSuccess_cd());
        result.setSuccess_cd_nm(his.getSuccess_cd_nm());
        result.setSuccess_per(his.getSuccess_per());
        result.setTotal_q_cnt(his.getTotal_q_cnt());
        result.setCorrect_cnt(his.getCorrect_cnt());
        result.setWrong_cnt(his.getWrong_cnt());
        result.setCorrect_per(his.getCorrect_per());
        result.setStart_dt(his.getStart_dt());
        result.setEnd_dt(his.getEnd_dt());
        result.setAccum_sec(his.getAccum_sec());
        result.setTime_limit(his.getTime_limit());
        result.setResultDetails(questionResultDetails);

        return result;
    }

    public List<CategoryResDto> findCategoryHis(Long user_id) {
        List<CategoryResDto> categoryHis = userApiMapper.findCategoryHis(user_id);
        return categoryHis;
    }

    public List<CategoryStatsResDto> findCategoryStats(Long user_id, Long category_id) {
        log.info("user_id: {}, category_id: {}", user_id, category_id);
        List<CategoryStatsResDto> categoryStats = userApiMapper.findCategoryStats(user_id, category_id);
        log.info("categoryStats = {}", categoryStats);
        return categoryStats;

    }


}
