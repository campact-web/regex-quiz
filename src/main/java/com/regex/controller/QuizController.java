package com.regex.controller;

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
        List<Quiz> list = quizMapper.selectAll();
        model.addAttribute("quiz",list);
        return "quiz";
    }
	
//	@RequestMapping("/quiz")
//	public String quiz() {
//        return "quiz";
//    }
}
