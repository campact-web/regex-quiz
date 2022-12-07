package com.regex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class ResultController {
    @RequestMapping("/result")
    public String getResult(Model model, HttpServletRequest request, SessionStatus status) {
        // セッションから正答数を取得する
        HttpSession session = request.getSession();
        int answerNum = (int)session.getAttribute("answerNum");
        model.addAttribute("answerNum", answerNum);
        // セッションクリア
        session.invalidate();
        return "result";
    }
}