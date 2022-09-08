package com.regex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.regex.entity.Quiz;

@Mapper
public interface QuizMapper {

    // クイズを全件取得
    public List<Quiz> selectAll();

}