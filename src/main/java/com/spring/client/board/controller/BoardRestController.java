package com.spring.client.board.controller;

import com.spring.client.board.domain.Board;
import com.spring.client.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardRestController {
    private final BoardService boardService;

    @GetMapping("/list")
    public List<Board> list(Board board) {
        return boardService.boardList(board);
    }
}
