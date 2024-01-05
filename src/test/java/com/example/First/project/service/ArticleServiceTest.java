package com.example.First.project.service;

import com.example.First.project.dto.ArticleForm;
import com.example.First.project.entity.Article;
import org.junit.jupiter.api.Test;      //Test패키지 임포트
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;       //앞으로 사용할 수 있는 패키지 임포트
@SpringBootTest    //해당 클래스를 스프링 부트와 연동해 테스트
class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;      //articleService 객체 주입
    @Test       //해당 메소드가 테스트 코드임을 선언
    void index() {
        //1. 예상 데이터
        Article a = new Article(1L, "강민준", "aaaa");     //예상 데이터를 객체로 저장
        Article b = new Article(2L, "김상학", "bbbb");
        Article c = new Article(3L, "박지수", "cccc");

        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));      //a,b,c 합치기

        //2. 실제 데이터
        List<Article> articles = articleService.index();

        //3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공_존재하는_ID_입력() {
        //1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "강민준", "aaaa");

        //2. 실제 데이터
        Article article = articleService.show(id);

        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void show_실패_존재하지_않는_ID_입력() {
        //1. 예상 데이터
        Long id = -1L;
        Article expected = null;

        //2. 실제 데이터
        Article article = articleService.show(id);

        //3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공_title과_content만_있는_dto_입력() {
        //1. 예상 데이터
        String title = "김경민";
        String content = "eeee";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        //2. 실제 데이터
        Article article = articleService.create(dto);

        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_실패_id가_포함된_dto_입력() {
        //1. 예상 데이터
        Long id = 4L;
        String title = "김경민";
        String content = "eeee";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;

        //2. 실제 데이터
        Article article = articleService.create(dto);

        //3. 비교 및 검증
        assertEquals(expected, article);
    }
}