package com.spring.client.comment.repository;

import com.spring.client.article.domain.Article;
import com.spring.client.comment.domain.Comment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CommentRepositoryTests {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void commentInsertTest() {
        Article article = new Article();
        article.setNo(1L);

        Comment comment = new Comment();
        comment.setNickname("늘한봄");
        comment.setBody("우리 인생은 우리들이 노력한만큼 가치가 있다.");
        comment.setArticle(article);
        log.info("### comment 테이블에 첫번째 데이터 입력");
        commentRepository.save(comment);

        Comment comment1 = new Comment();
        comment1.setNickname("홀길동");
        comment1.setBody("실패한 자가 패배하는 것이 아니라 포기한 자가 패배하는것이다.");
        comment1.setArticle(article);
        log.info("### comment1 테이블에 두번째 데이터 입력");
        commentRepository.save(comment1);

        Comment comment2 = new Comment();
        comment2.setNickname("강희수");
        comment2.setBody("단 한번의 노력으로 자기의 바람을 성취할 수 없다. 또한 단 한번의 실패로 그 소망을 모두 포기할 수도 없는 것이다.");
        comment2.setArticle(article);
        log.info("### comment2 테이블에 세번째 데이터 입력");
        commentRepository.save(comment2);

        Comment comment3 = new Comment();
        comment3.setNickname("김영희");
        comment3.setBody("말이 입힌 상처는 칼이 입힌 상처보다 깊다.");
        comment3.setArticle(article);
        log.info("### comment3 테이블에 네번째 데이터 입력");
        commentRepository.save(comment3);
    }

    @Test
    public void commentListTest() {
        List<Comment> commentList = commentRepository.articleNoCommentList(1L);
        commentList.forEach(comment -> log.info(comment.toString()));
    }

    @Test
    public void nicknameSelectTest() {
        List<Comment> commonList = commentRepository.findByNickname("홍길동");
        commonList.forEach(comment -> log.info(comment.toString()));
    }
}
