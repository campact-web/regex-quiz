package com.regex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.regex.entity.Quiz;

@Mapper
public interface QuizMapper {

    public List<Quiz> selectAll();
}