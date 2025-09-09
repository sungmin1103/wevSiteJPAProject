package com.spring.client.article.service;

import com.spring.client.article.domain.Article;
import com.spring.client.article.repository.ArticleRepository;
import com.spring.client.board.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    public final ArticleRepository articleRepository;

    @Override
    public List<Article> articleList(Article article) {
        List<Article> articleList = (List<Article>) articleRepository.findAll();
        return articleList;
    }

    @Override
    public void articleInsert(Article article) {
        articleRepository.save(article);
    }

    @Override
    public Article getArticle(Long no) {
        return articleRepository.findById(no)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 존재하지 않습니다."));
    }

    @Override
    @Transactional
    public Article articleHitUpdate(Article article) {
        Article dataArticle = getArticle(article.getNo());
        dataArticle.setHit(dataArticle.getHit() + 1);
        articleRepository.save(dataArticle);
        return dataArticle;
    }

    @Override
    public Article articleDetail(Article article) {
        Article detail =articleHitUpdate(article);
        return detail;
    }

    @Override
    public void articleUpdate(Article article) {
        Optional<Article> articleOptional = articleRepository.findById(article.getNo());
        Article updateArticle = articleOptional.orElseThrow();
        updateArticle.setTitle(article.getTitle());
        updateArticle.setContent(article.getContent());

        articleRepository.save(updateArticle);
    }


    @Override
    public void articleDelete(Article article) {
        articleRepository.deleteById(article.getNo());
    }
}


