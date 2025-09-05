package com.spring.client.board.repository;

import com.spring.client.board.domain.Board;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Slf4j
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    /* 게시판 등록 - save(): 주어진 엔티티를 저장 */
    @Test
    public void boardInsertTest() {
        Board board = new Board();
        board.setName("늘한봄");
        board.setTitle("노력 명언");
        board.setContent("우리 인생은 우리들이 노력한만큼 가치가 있다.");
        board.setRegDate(LocalDateTime.now());

        log.info("### board 테이블에 첫번째 데이터 입력");
        boardRepository.save(board);

        Board board1 = new Board();
        board1.setName("홍길동");
        board1.setTitle("끈기 명언");
        board1.setContent("실패한 자가 패배하는 것이 아니라 포기한 자가 패배하는 것이다.");
        board.setRegDate(LocalDateTime.now());

        log.info("### board 테이블에 두번째 데이터 입력");
        boardRepository.save(board1);

        Board board2 = new Board();
        board2.setName("강희수");
        board2.setTitle("끈기 명언");
        board2.setContent("단 한번의 노력으로 자기의 바람을 성취할 수 없다. 또한 단 한번의 실패로 그 소망을 모두 포기할 수도 없는 것이다.");

        log.info("### board 테이블에 세번째 데이터 입력");
        boardRepository.save(board2);

        Board board3 = new Board();
        board3.setName("강희수");
        board3.setTitle("언어 명언");
        board3.setContent("말이 입힌 상처는 칼이 입힌 상처보다 깊다.");

        log.info("### board 테이블에 네번째 데이터 입력");
        boardRepository.save(board3);
    }
    /* 게시판 전체 레코드 수 구하기 - count(): 사용가능한 엔티티 수를 반환*/

    @Test
    public void boardCountTest() {
        long boardCount = boardRepository.count();
        log.info("레코드 수: {}", boardCount);
    }

    /* 게시판 리스트 * findAll(): T타입의 모든 인스턴스를 반환*/
    @Test
    public void boardListTest() {
        List<Board> boardList = (List<Board>) boardRepository.findAll();
        boardList.forEach(board -> log.info(board.toString()));
    }

    /* 게시판 상세 조회 - fondVyId(Id id); ID로 엔티티를 검색. */
    @Test
    public void boardDetailTest() {
        Optional<Board> boardOptional = boardRepository.findById(1L);
        // isPresent() 메소드를 사용하여 Optional 객체에 저장된 값이 null인이 아닌지를 먼저 확인
        if(boardOptional.isPresent()) {
            Board board = boardOptional.get();
            log.info("1번글의 데이터: {}", board);
        }
    }

    /* 이 코도는 JUnit 테스트에서 사용되는 단정문(assertion)으로,
    boardoptional,isPresent()가 false여야 테스트가 통과한다는 뜻.
    즉, boardOptional에 값이 없어야(null이어야) 이 테스트는 성공이다.*/
    @Test
    public void boardNotFoundTest() {
        Optional<Board> boardOptional = boardRepository.findById(9L);
        assertFalse(boardOptional.isPresent()); //  JUnit 사용
    }

    /* 게시판 수정- findById() 메서드로 데이터를 가져와서 변경할 필드만 설정해 주면 된다. */
    @Test
    public void boardUpdateTest() {
        Optional<Board> boardOptional = boardRepository.findById(2L);

        if(boardOptional.isPresent()) {
            Board board = boardOptional.get();
            board.setTitle("힘들때 힘이 되는 명언");
            board.setContent("조급해 하지 말고 조바심내지 말고, 할 수 있는 만큼 최선을 다하자.");

            log.info("### board 테이블에 데이터 수정");
            boardRepository.save(board);
        }
    }

    /* 게시판 삭제 - deleteById(ID id) : 주어진 ID를 가진 엔티티를 삭제*/
    @Test
    public void boardDeleteTest() {
        boardRepository.deleteById(4L);
    }

    @Test
    public void findByTitleContainingTest() {
        //Board titleSearch = boardRepository.findByTitle("언어 명언");
        //log.info(titleSearch.toString());

        // 제목검색
        //List<Board> list = boardRepository.findByTitleContaining("언어");
        // 이름검색
        //List<Board> list = boardRepository.findByNameContaining("희수");
        // 내용검색
        //List<Board> list = boardRepository.findByContentContaining("우리");

        // 등록일검색
        /*log.info(LocalDateTime.now().minusDays(2).toString());*/
        log.info(LocalDateTime.now().toString());
        List<Board> list = boardRepository.findByRegDateBetween(LocalDateTime.now().minusDays(2), LocalDateTime.now());

        list.forEach(board -> log.info(board.toString()));
    }

    @Test
    public void findByOrderByNoDescTest() {
        // findByOrderByNoDesc(): 내림차순으로 번호 정렬
        List<Board> boardList = boardRepository.findByOrderByNoDesc();
        boardList.forEach(board -> log.info(board.toString()));
    }

    @Test
    public void findAllByPageAndSort() {
        org.springframework.data.domain.Pageable pageRequest = PageRequest.of(0, 10, Sort.Direction.DESC, "no");
        Page<Board> boardPage = boardRepository.findAll(pageRequest);
        boardPage.forEach(board -> log.info(board.toString()));
    }

    @Test
    public void findByTitleContainingWithPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "no");
        Page<Board> result = boardRepository.findByTitleContaining("끈기", pageable);
        result.forEach(board -> log.info(board.toString()));
    }

    @Test
    public void boardAllInsertTest() {
        for (int i = 1; i <= 100; i++) {
            Board board = new Board();
            board.setTitle("Title.." + i);
            board.setName("홍길동 + i");
            board.setContent("실패한 자가 패배하는것이 아니라 포기한자가 패배하는 것이다.");
            boardRepository.save(board);
        }
    }
}