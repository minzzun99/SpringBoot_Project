package com.example.First.project.controller;

import com.example.First.project.dto.CommentDto;
import com.example.First.project.entity.Article;
import com.example.First.project.repository.ArticleRepository;
import com.example.First.project.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.First.project.dto.ArticleForm;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

//로깅 기능을 위한 어노테이션 Simple Logging Facade For Java
@Controller
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;      //서비스 객체 주입

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";          //폴더 주소 {article폴더의 new.mustache 파일을 리턴함}
    }

    @PostMapping("/articles/create")
    public String createArticles(ArticleForm form) {
        log.info(form.toString());
        /*System.out.println(form.toString());*/
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());
        /*System.out.println(article.toString());*/
        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        /*System.out.println(saved.toString());*/
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);         //id를 잘 받았는지 로그 찍기
        //1. id를 조회해서 DB에서 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);
        /* 다른 방법
        Optional<Article> articleEntity = articleRepository.findById(id);
        */
        //2. 모델에 가져온 데이터 등록하기
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);
        //3. 뷰페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        //1.모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        //2.모델에 가져온 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        //3.뷰페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit (@PathVariable Long id, Model model) {
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        //뷰페이지 (edit.mustache)에서 사용하기 위해서 모델에 데이터 등록
        model.addAttribute("article", articleEntity);
        //뷰 페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());
        //1. DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        //2. 엔티티를 DB에 저장하기
        //2-1. DB에서 기존 데이터 가져오가
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        //2-2. 기존 데이터 값을 갱신하기
        if (target != null) {
            articleRepository.save(articleEntity);
        }

        //3. 수정결과 페이지로 리다이렉트하기
        return "redirect:/articles/" + articleEntity.getId();
    }
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다!!");
        //1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. 대상 엔티티 삭제하기
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", id+"번 게시물이 삭제됐습니다!");
        }
        //3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }
 }
