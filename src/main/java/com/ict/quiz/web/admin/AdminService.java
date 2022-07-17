package com.ict.quiz.web.admin;

import com.ict.quiz.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminMapper adminMapper;
    public List<QuestionPage> findAllQuestions(QuestionPage question) {

        int cnt = adminMapper.findAllQuestionsCount(question);
        Pagination pagination = new Pagination(question);
        pagination.setTotalRecordCount(cnt);

        question.setPagination(pagination);

        List<QuestionPage> allQuestions = adminMapper.findAllQuestions(question);


        return allQuestions;
    }

    public void addQuestion(Question q, List<QuestionOption> o) throws IOException {

        //adminMapper.addQuestion(q);

        log.info("cnt bf = {}", o.size());
        o.removeIf(item -> item.getSeq() == 0);
        o.sort(Comparator.comparing(QuestionOption::getSeq));

        log.info("cnt af = {}", o.size());
        for (QuestionOption option : o) {
            log.info("option = {}", option);
            //option.setQuestion_id(q.getId());
            //adminMapper.addOption(option);
        }

//        String oriImgName = upload.getOriginalFilename();
//        String imgName = "";
//
//        UUID uuid = UUID.randomUUID();
//        imgName = uuid+"_"+oriImgName;
//        String savePath = "files";
//
//
//        if(!new File(savePath).exists()) {
//            new File(savePath).mkdir();
//        }
//        String filePath = savePath+"\\"+imgName;
//        upload.transferTo(new File(filePath));
//
//        log.info("path = {}", filePath);
    }

    public void updateQuestion(Question q, List<QuestionOption> o) {
        adminMapper.updateQuestion(q);

        for (QuestionOption option : o) {
            option.setQuestion_id(q.getId());
            adminMapper.updateOption(option);
        }
    }
}
