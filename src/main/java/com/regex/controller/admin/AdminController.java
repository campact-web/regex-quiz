package com.regex.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.regex.mapper.QuizMapper;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
    QuizMapper quizMapper;

	@RequestMapping(value="new")
	public String getQuizNew() {
		return "admin/new";
	}
//    @RequestMapping(value="/quiz")
//    public String index(Model model) {
//        List<Quiz> list = quizMapper.selectAll();
//        model.addAttribute("quiz",list);
//        return "quiz";
	
}
