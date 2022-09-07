package com.ict.quiz.api.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ict.quiz.domain.*;
import com.ict.quiz.domain.api.UserAddReqDto;
import com.ict.quiz.domain.api.UserLoginReqDto;
import com.ict.quiz.domain.api.UserQuestionHisDetailResDto;
import com.ict.quiz.domain.api.UserQuestionHisResDto;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


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
        HisDetail his = userApiMapper.findHisDetail(id);

        List<QuestionResultDetail> questionResultDetails = new LinkedList<>();

        String[] q_set = his.getQuestion_set().split(",");
        String[] a_set = his.getAnswer_set().split(",");
        String[] c_set = his.getCorrect_set().split(",");

        for (int i = 0; i < q_set.length; i++) {
            String q = q_set[i];
            // 문제 조회
            ObjectMapper om = new ObjectMapper();
            QuestionResultDetail questionResultDetail = new QuestionResultDetail();
            Question question = questionApiMapper.findById(Long.valueOf(q));
            QuestionResult questionResult = om.convertValue(question, QuestionResult.class);

            questionResult.setCorrect_yn("1".equals(c_set[i]) ? "Y" : "N");
            if(question.getImage() != null &&question.getImage().length > 0) {
                String image = Base64.encodeBase64String(question.getImage());
                questionResult.setEncoded_image(image);
            }
            questionResultDetail.setQuestion(questionResult);

            List<QuestionOption> options = questionApiMapper.findOptionByQuestionId(Long.valueOf(q));
            List<QuestionOptionResult> questionOptionResults = new LinkedList<>();
            for (QuestionOption option : options) {
                QuestionOptionResult questionOptionResult = om.convertValue(option, QuestionOptionResult.class);
                String[] split = a_set[i].split(":");
                boolean b = Arrays.stream(split).anyMatch(a -> String.valueOf(option.getId()).equals(a));
                //log.info("question = {}, a_set[i] = {}, option_id = {}, boolean = {}", q, a_set[i], option.getId(), b);
                if(b) {
                    questionOptionResult.setSelect_yn("Y");
                } else {
                    questionOptionResult.setSelect_yn("N");
                }
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


}
