package com.spring.client.article.repository;

import com.spring.client.article.domain.Article;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Slf4j
public class ArticleRepositoryTests {
    @Autowired
    private ArticleRepository articleRepository;

    /* 게시판 등록 -save(): 주어진 엔티티를 저장 */
    @Test
    public void articleInsertTest() {
        Article article = new Article();
        article.setName("국내놀이공원");
        article.setTitle("놀이공원");
        article.setContent("에버랜드가 재밌을까 롯데월드가 재밌을까?");
        article.setRegDate(LocalDateTime.now());

        log.info("### article 테이블에 첫번째 데이터 입력 ");
        articleRepository.save(article);

        Article article1 = new Article();
        article1.setName("홍길동");
        article1.setTitle("홍길동은 의적인가 도적인가");
        article1.setContent("사람마다 다른거 같다.");
        article.setRegDate(LocalDateTime.now());

        log.info("### board 테이블에 두번째 데이터 입력");
        articleRepository.save(article1);

        Article article2 = new Article();
        article2.setName("강백호");
        article2.setTitle("농구에 관하여");
        article2.setContent("왼손은 거둘뿐");

        log.info("### article 테이블에 세번째 데이터 입력");
        articleRepository.save(article2);

        Article article3 = new Article();
        article3.setName("나루토");
        article3.setTitle("닌자");
        article3.setContent("나뭇잎마을은 어떤 곳인가");

        log.info("### article 테이블에 네번째 데이터 입력");
        articleRepository.save(article3);
    }

    /* 게시판 리스트 - findAll(): T타입의 모든 인스턴스를 반환.*/
    @Test
    public void articleListTest() {
        List<Article> articleList = articleRepository.findAll();
        articleList.forEach(article -> log.info(article.toString()));
    }

}
