package com.regex.controller.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.regex.dto.QuizDto;
import com.regex.entity.Quiz;
import com.regex.form.QuizForm;
import com.regex.mapper.QuizMapper;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
    QuizMapper quizMapper;
	
	/* サクセスメッセージフラグ*/
	private boolean successMsgFlg = false;

	/* 
	 * クイズ一覧画面
	 * 機能: 初期表示
	 * */
	@RequestMapping(value="index")
	public String getQuizNew(Model model) {
        Object successMsgFlg = model.getAttribute("successMsgFlg");
        if (successMsgFlg != null) {
            model.addAttribute("successMsg", String.valueOf(successMsgFlg));
        }
		List<Quiz> list = quizMapper.selectAll();
		model.addAttribute("quizList", list);
		return "admin/index";
	}
	
	/* 
	 * クイズ新規登録画面
	 * 機能: 初期表示
	 * */
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String getQuizAdd(@ModelAttribute QuizForm form, Quiz quiz, Model model) {
		return "admin/new";
	}
	
	/* 
	 * クイズ新規登録画面
	 * 機能: 画面からクイズを登録する
	 * */
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String postQuizAdd(@ModelAttribute QuizForm form, Quiz quiz, RedirectAttributes redirectAttributes) {

		try {
			quizMapper.insertQuiz(form);	
			//登録完了後、メッセージフラグをtrueに設定する
			successMsgFlg = true;
			//リダイレクト先にパラメータを渡す
			redirectAttributes.addFlashAttribute("successMsgFlg", successMsgFlg);
		} catch(Exception e) {
		      System.out.println("例外が発生しました。");
		      System.out.println(e);
		}
		return "redirect:/admin/index";
	}

	/*
	 *  クイズ新規登録画面
	 * 機能: csvファイルからクイズを一括登録する
	 * */
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String uploadFile(@RequestParam("file") MultipartFile uploadFile, RedirectAttributes redirectAttributes) {
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(uploadFile.getInputStream(), StandardCharsets.UTF_8))){
	      String line;
	      while ((line = br.readLine()) != null) {
	        final String[] split = line.split(",");
	        final QuizDto quizDto = 
	        		QuizDto.builder().quiz((split[0])).answer1(split[1]).answer2(split[2]).answer3(split[3]).build();
	        quizMapper.createCsvQuiz(quizDto);
			//登録完了後、メッセージフラグをtrueに設定する
			successMsgFlg = true;
			//リダイレクト先にパラメータを渡す
			redirectAttributes.addFlashAttribute("successMsgFlg", successMsgFlg);
	      }
	    } catch (IOException e) {
	      throw new RuntimeException("ファイルが読み込めません", e);
	    }

	    return "redirect:/admin/index";
	  }
	
	/*
	 * クイズ詳細画面
	 * 機能: クイズ詳細画面を表示する
	 */
	@RequestMapping(value="/show/{id}", method=RequestMethod.GET)
	public String showQuiz(@PathVariable int id, Model model) {
		//リファクタリング。サービスクラスを作成すること。
		Quiz quizset = quizMapper.selectQuizset(id).get(0);
		model.addAttribute("quizset", quizset);
		
		return "admin/show";
	}
	
	/*
	 * クイズ編集画面
	 * 機能: クイズ編集画面を表示する
	 */
	@RequestMapping(value="/edit")
	public String editQuiz() {
		return "admin/edit";
	}
	
}
