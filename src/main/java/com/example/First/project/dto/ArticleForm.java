package com.example.First.project.dto;

import com.example.First.project.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private String title;
    private String content;

    /*public ArticleForm(String title, String content) {        @AllArgsConstructor를 사용해서 생성자 자동 생성
        this.title = title;
        this.content = content;
    }*/

    /*@Override
    public String toString() {          @ToString을 사용해서 toString 메소드 자동 생성
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }*/

    public Article toEntity() {
        return new Article(null, title, content);
    }
}
