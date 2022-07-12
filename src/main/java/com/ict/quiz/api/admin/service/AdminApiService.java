package com.ict.quiz.api.admin.service;

import com.ict.quiz.api.admin.mapper.AdminApiMapper;
import com.ict.quiz.dto.Question;
import com.ict.quiz.dto.QuestionOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminApiService {

    private final AdminApiMapper adminMapper;

    public void createQuestion(Question question, MultipartFile multipartFile) {
    }

    public void createQuestionOptions(List<QuestionOption> options) {

        for (QuestionOption option : options) {

        }
    }
}
