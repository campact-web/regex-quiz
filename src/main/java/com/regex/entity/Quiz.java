package com.regex.entity;

import java.util.List;

import lombok.Data;

@Data
public class Quiz {
    private int id;
    private String quiz;
    private List<Choice> choices;
//	/* 登録者*/
//	private String registed_by;
//	/* 作成日*/
//	private String created_at;
//	/* 更新者*/
//	private String updated_by;
//	/* 更新日*/
//	private String updated_at;
//
//	private String is_deleted;
}