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

import java.util.List;

@SpringBootTest
@Slf4j
public class BoardRepositoryJPQLTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void boardListTest() {
        List<Board> boardList = boardRepository.boardList();
        boardList.forEach(board -> log.info(board.toString()));

    }

    @Test
    public void boardDetailTest() {
        Board board = boardRepository.boardDetail(1L);
        log.info("데이터 조회: {}", board.toString());
    }

    @Test
    public void boardListPagingTest() {
        Pageable pageRequest = PageRequest.of(0, 10, Sort.Direction.DESC,"no");
        // 페이징요청 정보를 생성하여 매개변수로 전달하고 검색결과를 페이지 형태로 받아온다.
        Page<Board> page = boardRepository.boardListPaging(pageRequest);
        // 검색결과는 페이지네이션에 필요한 정보(페이지번호, 페이지크기, 다음과 이전 페이지)와
        // 페이지 크기로 나뉘어진 리스트이다
        int totalPages = page.getTotalPages();

        log.info("page: {}", page.toString());
        log.info("totalPages : {}", totalPages);

        Pageable pageable = page.getPageable();

        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();

        log.info("pageable: {}", pageable.toString());
        log.info("pageNumber: {}", pageNumber);
        log.info("pageSize: {}", pageSize);

        List<Board> boardList = page.getContent();
        boardList.forEach(board -> log.info(board.toString()));
    }

    /* 네이티브 SQL */
    @Test
    public void boardListAllTest() {
        List<Board> boardAllList = boardRepository.boardAllList();
        boardAllList.forEach(board -> log.info(board.toString()));
    }

    @Test
    public void boardAllByPagingTest() {
        Pageable pageRequest = PageRequest.of(0, 10, Sort.Direction.DESC,"no");
        // 페이징요청 정보를 생성하여 매개변수로 전달하고 검색결과를 페이지 형태로 받아온다.
        Page<Board> page = boardRepository.findAll(pageRequest);
        // 검색결과는 페이지네이션에 필요한 정보(페이지번호, 페이지크기, 다음과 이전 페이지)와
        // 페이지 크기로 나뉘어진 리스트이다
        int totalPages = page.getTotalPages();

        log.info("page: {}", page.toString());
        log.info("totalPages : {}", totalPages);

        Pageable pageable = page.getPageable();

        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();

        log.info("pageable: {}", pageable.toString());
        log.info("pageNumber: {}", pageNumber);
        log.info("pageSize: {}", pageSize);

        List<Board> boardList = page.getContent();
        boardList.forEach(board -> log.info(board.toString()));
    }
}
