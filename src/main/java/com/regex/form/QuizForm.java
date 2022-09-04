package com.regex.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class QuizForm {
	private int id;
	private String quiz;
	private String answer1;
	private String answer2;
	private String answer3;
	
	/* 登録者*/
	private String registed_by;
	/* 作成日*/
	private String created_at;
	/* 更新者*/
	private String updated_by;
	/* 更新日*/
	private String updated_at;
	
	private String is_deleted;
}