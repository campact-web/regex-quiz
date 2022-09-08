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
        List<Quiz> quizList = quizMapper.selectAll();

        // シャッフルする
        Collections.shuffle(quizList);

        // 選択肢シャッフル用のリストを作成
        List<String> choiceList = new ArrayList<>();
        List<String> tmpList = new ArrayList<>();

        // 選択肢をシャッフル＋10問に絞り込む
        for (int i = 0; i < 10; i++) {
            tmpList.add(quizList.get(i).getAnswer1());
            tmpList.add(quizList.get(i).getAnswer2());
            tmpList.add(quizList.get(i).getAnswer3());
            Collections.shuffle(tmpList);
            choiceList.addAll(tmpList);
            tmpList.clear();
            System.out.println(quizList);
            System.out.println(choiceList);
        }

        model.addAttribute("quizList", quizList);
        model.addAttribute("choiceList", choiceList);
        return "quiz";
    }
}
