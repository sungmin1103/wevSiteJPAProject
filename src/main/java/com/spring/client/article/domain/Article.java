package com.spring.client.article.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "boot_article")
@SequenceGenerator(name = "article_generator",
        sequenceName = "boot_article_seq", initialValue = 1, allocationSize = 1)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_generator")
    private Long no;

    /* 객체 필드를 테이블 컬럼에 매핑 */
    @Column(length = 15, nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    @ColumnDefault(value = "sysDate")
    private LocalDateTime regDate;

    @ColumnDefault(value = "0")
    private Integer hit = 0;
}
