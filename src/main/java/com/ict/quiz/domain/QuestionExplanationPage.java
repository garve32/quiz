package com.ict.quiz.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionExplanationPage extends Criteria {
    
    private Long id;
    private Long question_id;
    private String explanation_text;
    private String image_name;
    private byte[] image;
    private String use_yn;

    /** 페이징 정보 */
    public Pagination pagination;
    
    // 조회를 위한 필드들
    private String question_text; // Question 테이블의 text 컬럼
    private String category_name; // Category 테이블의 name 컬럼
    private Long category_id;     // 검색 조건용
    private String searchKeyword; // 검색 조건용
}
