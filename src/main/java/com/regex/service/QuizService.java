package com.regex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.regex.entity.Quiz;
import com.regex.mapper.QuizMapper;

@Service
public class QuizService {

    @Autowired
    QuizMapper quizMapper;

    // 10問ランダムに取得する
    public List<Quiz> selectQuiz10() {
        return quizMapper.selectQuiz10();
    }
}
