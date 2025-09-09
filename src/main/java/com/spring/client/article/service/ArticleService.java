package com.spring.client.article.service;

import com.spring.client.article.domain.Article;
import com.spring.client.common.dto.PageRequestDTO;
import com.spring.client.common.dto.PageResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {
        public List<Article> articleList(Article article);
        public void articleInsert(Article article);
        public Article getArticle(Long no);
        public Article articleHitUpdate(Article article);       // HitUpdate를 한 이유는 조회수까지 같이 올라가야해서
        public Article articleDetail(Article article);
        public void articleUpdate(Article article);
        public void articleDelete(Article article);
    }
