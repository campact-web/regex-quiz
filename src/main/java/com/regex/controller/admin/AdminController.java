package com.regex.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.regex.entity.Quiz;
import com.regex.form.QuizForm;
import com.regex.mapper.QuizMapper;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
    QuizMapper quizMapper;

	@RequestMapping(value="index")
	public String getQuizNew(Model model) {
		List<Quiz> list = quizMapper.selectAll();
<<<<<<< HEAD
		model.addAttribute("quizList", list);
=======
		model.addAttribute("quizes", list);
>>>>>>> e31d31d95f12004a9100cd6602dbcd85c59141b1
		return "admin/index";
	}
	@RequestMapping(value="/new")
	public String postQuizAdd(@ModelAttribute QuizForm form, Quiz quiz) {
		/*画面から入力された値をもとに新規でクイズを登録する*/
		quizMapper.insertQuiz(form);
<<<<<<< HEAD
=======
		quizMapper.insertChoice(form);
>>>>>>> e31d31d95f12004a9100cd6602dbcd85c59141b1
		return "redirect:/admin/index";
	}
	
}
