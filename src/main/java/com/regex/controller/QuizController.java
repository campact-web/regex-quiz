package com.regex.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.regex.entity.Quiz;
import com.regex.service.QuizService;

@Controller
public class QuizController {

    @Autowired
    QuizService quizService;

    /* 問題リスト */
    private List<Quiz> quizPlayList = new ArrayList<>();
    /* 問題の項番 */
    private int count = 0;
    /* 出題する問題 */
    private Quiz oneQuiz = null;
    /* ラジオボタン  */
    Map<Integer, String> radioAnswer = new HashMap<>();
    /* 正解数 */
    private int answerNum = 0;
    /* メッセージ */
    private String message = null;
    /* 答え合わせ済みフラグ*/
    private boolean answerCheckedFlg = false;

    /* クイズを出題する-10問 */
    @GetMapping(value = "/play")
    public String getQuizPlay(Model model, HttpServletRequest request, HttpServletResponse response) {
        // セッションの作成・取得
        HttpSession session = request.getSession();

        // 問題取得(第一問目)
        if(session.getAttribute("quizPlayList") == null) {
            // クイズ再度実行する際の不具合解消に必要
            count = 0;
            answerNum = 0;
            // 10問ランダムに取得する
            quizPlayList = quizService.selectQuiz10();
        }
        // セッションに問題リストが存在する場合（第二問目以降）
        else{
            // 10問出題済みなら結果画面に遷移する
            count = (int)session.getAttribute("count");
            if(count >= 10) {
                return "redirect:result";
            }
            quizPlayList = (List<Quiz>)session.getAttribute("quizPlayList");
        }
        count++;

        // 問題リストから1問取得する
        oneQuiz = quizPlayList.get(0);
        session.setAttribute("count", count);
        session.setAttribute("quizPlayList", quizPlayList);
        session.setAttribute("oneQuiz", oneQuiz);

        // 選択肢1~3をランダムに表示するためのリストを生成
        int[] random = { 1, 2, 3 };
        shuffle(random);
        session.setAttribute("random", random);

        // ラジオボタン
        radioAnswer.put(1, oneQuiz.getAnswer1());
        radioAnswer.put(2, oneQuiz.getAnswer2());
        radioAnswer.put(3, oneQuiz.getAnswer3());
        session.setAttribute("radioAnswer", radioAnswer);

        answerCheckedFlg = false;

        model.addAttribute("answerCheckedFlg", answerCheckedFlg);
        return "play";
    }

    private static void shuffle(int[] quizNumber) {
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

    /* 答え合わせする */
    @PostMapping(value = "/play", params = "answer")
    public String checkAnswer(@RequestParam String selectedAnswer, Model model, HttpServletRequest request, HttpServletResponse response) {
        // セッション情報を取得
        HttpSession session = request.getSession();
        oneQuiz = (Quiz)session.getAttribute("oneQuiz");
        radioAnswer = (Map<Integer, String>)session.getAttribute("radioAnswer");
        // 出題した問題の正答を取得
        String answer = oneQuiz.getAnswer1();

        // 出題した問題をリストから削除する
        quizPlayList.remove(0);
        session.setAttribute("quizPlayList", quizPlayList);

        // 答え合わせを行う
        if (selectedAnswer.equals(answer)) {
            message = "正解です！！";
            answerNum++;
            session.setAttribute("answerNum", answerNum);
        } else {
            message = "不正解！！";
        }
        answerCheckedFlg = true;

        model.addAttribute("selectedAnswer", selectedAnswer);
        model.addAttribute("message", message);
        model.addAttribute("answerCheckedFlg", answerCheckedFlg);
        return "play";
    }
}
