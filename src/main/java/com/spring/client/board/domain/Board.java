package com.spring.client.board.domain;

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
/* JPA를 사용해서 테이블과 매핑할 클래스 -
이 어노테이션이 명시된 클래스는 JPA가 관리하는것으로 엔티티라 부른다 */
@Entity
/* 엔티티와 매핑할 테이블을 지정 - 생략하면 매핑할 엔티티 이름을 테이블명으로 사용.
    name 속성을 이용해서 테이블명 지정. */
 @Table(name = "boot_board")
@SequenceGenerator(
        name = "boot_board_generator",
        sequenceName = "boot_board_seq",
        initialValue = 1,
        allocationSize = 1)
public class Board {
     /* 기본키(엔티티를 식별할 때 사용할 필드를 지정할 때 사용)를 매핑*/
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boot_board_generator")
    private Long no;

    /* 객체 필드를 테이블 컬럼에 매핑*/
    @Column(length = 15, nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    /* 엔티티가 생성(@CreationTimestamp)되거나
       업데이트(@UpdateTimestamp)되는 시점의 날짜 데이터를 기록하는 설정 */
    @CreationTimestamp
    @ColumnDefault(value = "sysdate")
    private LocalDateTime regDate;

    @ColumnDefault(value = "0")
    private Integer hit = 0;

    // @Transient: 필드를 매핑하지 않을때 새용
    // 객체 타입을 사용하는 이뉴는 null을 리턴할 수 있기 때문이다.
}
