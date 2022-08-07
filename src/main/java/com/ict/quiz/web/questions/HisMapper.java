package com.ict.quiz.web.questions;

import com.ict.quiz.domain.HisDetail;
import com.ict.quiz.domain.HisForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisMapper {
    List<HisForm> getHisList(Long user_id);

    HisDetail getHisDetail(Long id);
}
