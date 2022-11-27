package com.regex.dto;

import lombok.Builder;
@Builder
public class QuizDto {
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
    /* 削除フラグ*/
    private String is_deleted;
}