package com.regex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.regex.entity.Quiz;
import com.regex.form.QuizForm;

@Mapper
public interface QuizMapper {

    public List<Quiz> selectAll();
    
    /* 問題を作成する*/
    public void insertQuiz(QuizForm form);
    
    /* 選択肢を作成する*/
    public void insertChoice(QuizForm form);
}