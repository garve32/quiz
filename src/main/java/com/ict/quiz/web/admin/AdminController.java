package com.ict.quiz.web.admin;

import com.ict.quiz.domain.Question;
import com.ict.quiz.domain.QuestionOption;
import com.ict.quiz.domain.QuestionPage;
import com.ict.quiz.domain.QuestionWithOptionReqDto;
import com.ict.quiz.web.questions.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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

    @ModelAttribute("categoryMap")
    public Map<String, String> categoryMap() {
        Map<String, String> categoryMap = new LinkedHashMap<>();
        categoryMap.put("3", "AWS DVA-C01");
        categoryMap.put("4", "ADsP");

        return categoryMap;
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
            model.addAttribute("image", image);
        }
        return "admin/addQuestion";
    }

    @GetMapping("/question/add")
    public String addQuestionForm(Model model) throws Exception {
        QuestionWithOptionReqDto dto = new QuestionWithOptionReqDto();
        Question question = new Question();
        question.setType("S");
        dto.setQuestion(question);
        model.addAttribute("q", dto);

        return "admin/addQuestion";
    }

    @PostMapping("/question/add")
    public String addQuestion(@Validated @ModelAttribute("q") QuestionWithOptionReqDto q, BindingResult result, MultipartFile upload) throws Exception {


        if (result.hasErrors()) {
            String errorMessage = "입력값을 확인하세요";
            log.info("errors={} ", result);
            if(result.hasFieldErrors()) {
                List<FieldError> errors = result.getFieldErrors();
                for (FieldError error : errors ) {
                    errorMessage += ", " + error.getField();
                    //log.info("error = {}", error);
                    //System.out.println (error.getObjectName() + " - " + error.getField());
                }
            }
            log.info(errorMessage);
            result.reject("-1", errorMessage);

            return "admin/addQuestion";
        }

        Question question = q.getQuestion();
        List<QuestionOption> options = q.getOptions();
        question.setImage(upload.getBytes());

        //log.info("question = {}", q);
        boolean hasCorrect = options.stream().anyMatch(item -> !item.getDel_yn().equals("Y") && item.getCorrect_yn().equals("Y"));
        if(!hasCorrect) {
            result.reject("-1", "정답을 한개 이상 표시하세요.");

            return "admin/addQuestion";
        }


        //adminService.addQuestion(question, options);

        for (QuestionOption option : options) {
            log.info("before options = {}", option);
        }

        // ID도 없고 seq가 0인 row는 생성했다가 그냥 삭제한 row
        options.removeIf(item -> item.getId() == null && item.getSeq() == 0);

        for (QuestionOption option : options) {
            log.info("after options = {}", option);
        }

        for (QuestionOption option : options) {
            // Option 아이디가 없으면 insert
            if(option.getId() == null) {
                log.info("insert option = {}", option);
            }
            // isDel == 'Y' 면 delete
            else if ("Y".equals(option.getDel_yn())) {
                log.info("delete option = {}", option);
            }
            // isDel != 'Y' 면 update
            else {
                log.info("update option = {}", option);
            }

            //option.setQuestion_id(q.getId());
            //adminMapper.addOption(option);
        }

        //adminService.updateQuestion(question, options);

        return "redirect:/admin/questions";
    }

    @PostMapping
    public String saveQuestion() {
        return null;
    }
}
