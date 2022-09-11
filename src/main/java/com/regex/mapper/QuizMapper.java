package com.regex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.regex.entity.Quiz;
import com.regex.form.QuizForm;

@Mapper
public interface QuizMapper {

    // クイズを全件取得
    public List<Quiz> selectAll();
    
<<<<<<< HEAD
    // クイズを新規作成
    public void insertQuiz(QuizForm form);
=======
    /* 問題を作成する*/
    public void insertQuiz(QuizForm form);
    
    /* 選択肢を作成する*/
    public void insertChoice(QuizForm form);
>>>>>>> e31d31d95f12004a9100cd6602dbcd85c59141b1
}