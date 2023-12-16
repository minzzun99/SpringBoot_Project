package com.example.First.project.repository;

import com.example.First.project.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();       //Iterable -> ArrayList로 수정하여 사용 ==> 오버라이딩
}
