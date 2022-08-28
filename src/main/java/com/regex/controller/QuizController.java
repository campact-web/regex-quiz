package com.regex.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.regex.entity.Quiz;
import com.regex.mapper.QuizMapper;

@Controller
public class QuizController {

    @Autowired
    QuizMapper quizMapper;

    @RequestMapping(value="/quiz")
    public String index(Model model) {
        // 問題を全て取得する
        List<Quiz> list = quizMapper.selectAll();

        // 取得した問題をシャッフルする
        Collections.shuffle(list);

        // 出題用リストの生成
        List<Quiz> questionList = new ArrayList<>();

        // 10問ずつ格納する
        for (int i = 0; i < 10;i++) {
            questionList.add(list.get(i));
        }

        model.addAttribute("quiz", questionList);
        return "quiz";
    }

//	@RequestMapping("/quiz")
//	public String quiz() {
//        return "quiz";
//    }
}
