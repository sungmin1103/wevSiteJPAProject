package com.spring.client.article.controller;

import com.spring.client.article.domain.Article;
import com.spring.client.article.service.ArticleService;
import com.spring.client.common.dto.PageRequestDTO;
import com.spring.client.common.dto.PageResponseDTO;
import com.spring.client.common.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/article/*")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final CustomFileUtil fileUtil; /* 필드 선언 */

    @GetMapping("/articleList")
    public String articleList(Article article, Model model) {
        List<Article> articleList = articleService.articleList(article);
        model.addAttribute("article", articleList);

        return "client/article/articleList";
    }

    @GetMapping("/insertForm")
    public String insertForm(Article article) {
        return "client/article/insertForm";
    }

    @PostMapping("/articleInsert")
    public String articleInsert(Article article) {
        articleService.articleInsert(article);
        return "redirect:/article/boardList";
    }

    @GetMapping("/{no}")
    public String articleDetail(@PathVariable Long no, Article article, Model model) {
        article.setNo(no);
        Article detail = articleService.articleDetail(article);
        model.addAttribute("detail", detail);

        /*String에서 줄바꿈(newline)은 Window에서 \r\n, Linux에서 \n으로 표현된다.
         * 하지만 이런 방식은, 서로 다른 종류의 OS에서 동작하는 프로그램에서 문제가 발생할 수 있다.
         * [참고]String#format()의 %n: String#format()에서 %n은 line separator를 의미한다.
         */
        String newLine = System.getProperty("line.separator").toString();
        model.addAttribute("newLine", newLine);

        return "client/article/articleDetail";
    }

    @PostMapping("/updateForm")
    public String updateForm(Article article, Model model) {
        Article updateData = articleService.getArticle(article.getNo());
        model.addAttribute("updateData", updateData);
        return "client/article/updateForm";
    }

    @PostMapping("/articleUpdate")
    public String articleUpdate(Article article) {
        articleService.articleUpdate(article);
        return "redirect:/article/"+article.getNo();
    }

    @PostMapping("/articleDelete")
    public String articleDelete(Article article) {
        articleService.articleDelete(article);
        return "redirect:/article/boardList";
    }
}
