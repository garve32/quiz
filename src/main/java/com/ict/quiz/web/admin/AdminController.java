package com.ict.quiz.web.admin;

import com.ict.quiz.domain.*;
import com.ict.quiz.web.questions.QuestionService;
import com.ict.quiz.web.validator.QuestionValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final QuestionService questionService;

    @ModelAttribute("categoryList")
    public List<Category> categoryMap() {
        return adminService.findAllCategories();
    }

    @ModelAttribute("useType")
    public Map<String, String> useType() {
        Map<String, String> useTypes = new LinkedHashMap<>();
        useTypes.put("Y", "사용");
        useTypes.put("N", "미사용");

        return useTypes;
    }

    @ModelAttribute("questionTypes")
    public Map<String, String> questionTypes() {
        Map<String, String> questionTypes = new LinkedHashMap<>();
        questionTypes.put("S", "단일 선택");
        questionTypes.put("M", "다중 선택");

        return questionTypes;
    }

    @GetMapping()
    public String adminForm(Model model) throws Exception {

        return "admin/adminForm";
    }

    @GetMapping("/questions")
    public String questions(@ModelAttribute("params") QuestionPage question, Model model) throws Exception {
        List<QuestionPage> questionList = adminService.findAllQuestions(question);
        model.addAttribute("list", questionList);
        return "admin/questions";
    }

    @GetMapping("/question/{id}")
    public String questionForm(@PathVariable("id") Long id, Model model) throws Exception {
        QuestionWithOptionReqDto dto = new QuestionWithOptionReqDto();

        Question q = questionService.findById(id);
        List<QuestionOption> options = questionService.findByQuestionId(id);

        dto.setQuestion(q);
        dto.setOptions(options);

        model.addAttribute("q", dto);

        if(q.getImage() != null &&q.getImage().length > 0) {
            String image = Base64.encodeBase64String(q.getImage());
            model.addAttribute("upload", image);
        }
        return "admin/detailQuestionForm";
    }

    @GetMapping("/question/add")
    public String addQuestionForm(Model model) throws Exception {
        QuestionWithOptionReqDto dto = new QuestionWithOptionReqDto();
        Question question = new Question();
        question.setType("S");
        dto.setQuestion(question);
        model.addAttribute("q", dto);

        return "admin/detailQuestionForm";
    }

    @PostMapping("/question/save")
    public String saveQuestion(@Validated @ModelAttribute("q") QuestionWithOptionReqDto q, BindingResult result, @RequestParam("upload") MultipartFile upload) throws Exception {

        if (result.hasFieldErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            log.info("errors={} ", result);

                List<FieldError> errors = result.getFieldErrors();
                for (FieldError error : errors ) {
                    if(!error.isBindingFailure()) {
                        errorMessage.append("<br>").append(error.getDefaultMessage());
                    } else {
                        String field = error.getField();
                        errorMessage.append("<br>").append(field).append("를 확인하세요.");
                    }
                }

            result.reject("-1", errorMessage.delete(0, 4).toString());

            return "admin/detailQuestionForm";
        }

        Question question = q.getQuestion();
        List<QuestionOption> options = q.getOptions();
        //question.setImage(upload.getBytes());

        boolean hasCorrect = options.stream().anyMatch(item -> !item.getDel_yn().equals("Y") && item.getCorrect_yn().equals("Y"));
        if(!hasCorrect) {
            result.reject("-1", "정답을 한개 이상 표시하세요.");
            return "admin/detailQuestionForm";
        }

        byte[] bytes = upload.getBytes();
        question.setImage(bytes);

        // insert 시 그냥 insert
        // update 시 file name이 없으면 그냥 update(원래 없었던지 이번에 삭제했던지)
        // update 시 file name이 있고 파일이 없으면? 파일업데이트 하면 안됨
        // update 시 file name이 있고 파일이 있으면? 파일업데이트 해야 됨
        if(question.getId() == null) {
            log.info("insert = {}", question);
            adminService.insertQuestion(question);
        } else {
            if(question.getImage_name().isEmpty()) {
                log.info("update with file = {}", question);
                adminService.updateQuestion(question);
            } else {
                if(upload.getSize() > 0) {
                    // 파일 업데이트 해야함
                    log.info("update with file = {}", question);
                    adminService.updateQuestion(question);
                } else {
                    // 파일 업데이트 하면 안됨
                    log.info("update without file = {}", question);
                    adminService.updateQuestionNotFile(question);
                }
            }
        }

        // ID도 없고 seq가 0인 row는 생성했다가 그냥 삭제한 row => 화면에서 처리했음
        // options.removeIf(item -> item.getId() == null && item.getSeq() == 0);
        options.sort(Comparator.comparing(QuestionOption::getSeq));

        for (QuestionOption option : options) {
            option.setQuestion_id(question.getId());
            // Option 아이디가 없으면 insert
            if(option.getId() == null) {
                log.info("insert option = {}", option);
                adminService.insertOption(option);
            } else {
                log.info("update option = {}", option);
                adminService.updateOption(option);
            }
        }

        return "redirect:/admin/questions";
    }

}
