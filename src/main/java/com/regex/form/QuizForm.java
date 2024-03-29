package com.regex.form;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
/* クイズ登録画面の入力フォーム*/
public class QuizForm {
	private int id;
	private String quiz;
	private String answer1;
	private String answer2;
	private String answer3;
	private Timestamp created_at;
	private Timestamp updated_at;
	private int quizNumber;
}