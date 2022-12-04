package com.regex.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Quiz {
	private int id;
	private String quiz;
	private String answer1;
	private String answer2;
	private String answer3;
	/* 登録者*/
	private String registed_by;
	/* 作成日*/
	private Timestamp created_at;
	/* 更新者*/
	private String updated_by;
	/* 更新日*/
	private Timestamp updated_at;
	
	private String is_deleted;
}