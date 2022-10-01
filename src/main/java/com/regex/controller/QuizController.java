package com.regex.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.regex.entity.Quiz;
import com.regex.form.QuizForm;
import com.regex.mapper.QuizMapper;

@Controller
public class QuizController {

	@Autowired
	QuizMapper quizMapper;
	
	
	private List<Quiz> quizPlayList = new ArrayList<>();
	private List<Quiz> quiz_session = null;
	private int i = 0;
	private Quiz oneQuiz = null;
	/* 正解数*/
	private int correctCount = 0;

	@RequestMapping(value = "/quiz")
	public String getUserQuiz(Model model) {
		List<Integer> quizNumberList = new ArrayList<Integer>();
		// 問題をランダムに10問取得する
		List<Quiz> quizList = quizMapper.selectQuiz10();
		Integer[] quizNumber = { 1, 2, 3 };
		List<Integer> tmpList = Arrays.asList(quizNumber);
		// 選択肢1~3をランダムに表示するためのリストを生成
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(tmpList);
			quizNumberList.addAll(tmpList);
		}
		model.addAttribute("quizList", quizList);
		model.addAttribute("quizNumberList", quizNumberList);
		return "quiz";
	}


	/* クイズを出題する-10問 */
	@RequestMapping(value = "/play")
	public String postQuizPlay(@ModelAttribute QuizForm quizForm, Model model, HttpServletRequest request, HttpServletResponse response) {
		//➀セッションの作成・取得
		HttpSession session = request.getSession();

		//➁セッションに前回の値があった場合（第二問目以降）
		if(session.getAttribute("quiz_session") != null) {
			//正誤判定を行う
//			System.out.println(str);
			if(quizForm.getAnswer1() != null) {
				//正解の場合、モーダル
				System.out.println("正解です");
				correctCount++;
				System.out.println(correctCount);
			}
			
			i = (int)session.getAttribute("count");
			if(i >= 10) {
				session.removeAttribute("count");
				session.removeAttribute("quiz_session");
				System.out.println("10問終了です。");
				return "result";
			}
			quizPlayList = (List<Quiz>)session.getAttribute("quiz_session");
		}
		//問題出題(初回)
		else{
			//10問ランダムに取得する
			quizPlayList = quizMapper.selectQuiz10();
		}
		i++;
		
		//問題リストから1問取得する
		oneQuiz = quizPlayList.get(0);
		// 出題した問題をリストから削除する
		quizPlayList.remove(0);
		session.setAttribute("count", i);
		session.setAttribute("quiz_session", quizPlayList);
//		
		// 選択肢1~3をランダムに表示するためのリストを生成
		int[] quizNumber = { 1, 2, 3 };
		shuffle(quizNumber); 
		
		model.addAttribute("oneQuiz", oneQuiz);
		model.addAttribute("quizNumber", quizNumber);
		return "play";
	}
	
	public static void shuffle(int[] quizNumber) {
	    // 配列が空か１要素ならシャッフルしようがないので、そのままreturn
	    if (quizNumber.length <= 1) {
	        return;
	    }

	    // Fisher–Yates shuffle
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = quizNumber.length - 1; i > 0; i--) {
	        int index = rnd.nextInt(i + 1);
	        // 要素入れ替え(swap)
	        int tmp = quizNumber[index];
	        quizNumber[index] = quizNumber[i];
	        quizNumber[i] = tmp;
	    }
	}
	
	
//  @RequestMapping(value="/quiz")
//  public String index(Model model) {
//      // 問題を全て取得する
//      List<Quiz> quizList = quizMapper.selectAll();
//
//      // シャッフルする
//      Collections.shuffle(quizList);
//
//      // 選択肢シャッフル用のリストを作成
//      List<String> choiceList = new ArrayList<>();
//      List<String> tmpList = new ArrayList<>();
//
//      // 選択肢をシャッフル＋10問に絞り込む
//      for (int i = 0; i < 10; i++) {
//          tmpList.add(quizList.get(i).getAnswer1());
//          tmpList.add(quizList.get(i).getAnswer2());
//          tmpList.add(quizList.get(i).getAnswer3());
//          Collections.shuffle(tmpList);
//          choiceList.addAll(tmpList);
//          tmpList.clear();
//      }
//
//      model.addAttribute("quizList", quizList);
//      model.addAttribute("choiceList", choiceList);
//      return "quiz";
//  }

}
