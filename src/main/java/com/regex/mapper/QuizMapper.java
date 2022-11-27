package com.regex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.regex.dto.QuizDto;
import com.regex.entity.Quiz;
import com.regex.form.QuizForm;

@Mapper
public interface QuizMapper {

    // クイズを全件取得
    public List<Quiz> selectAll();
    
    /* クイズをランダムに10問取得する*/
    public List<Quiz> selectQuiz10();
    
    /* クイズをランダムに1問取得する*/
    public List<Quiz> selectQuiz1();
    
    /* 問題を作成する*/
    public void insertQuiz(QuizForm form);
    
    /* csvファイルから問題を作成する*/
    public void createCsvQuiz(QuizDto dto);
    
}