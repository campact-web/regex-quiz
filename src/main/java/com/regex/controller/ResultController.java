package com.regex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResultController {
    @RequestMapping("/result")
    public String getResult(Model model, HttpServletRequest request) {
        // セッションから正答数を取得する
        HttpSession session = request.getSession();
        int correctCount = (int) session.getAttribute("session_correctCount");
        model.addAttribute("correctCount", correctCount);
        return "result";
    }
}