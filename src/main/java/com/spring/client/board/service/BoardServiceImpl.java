package com.spring.client.board.service;

import com.spring.client.board.domain.Board;
import com.spring.client.board.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    public final BoardRepository boardRepository;

    @Override
    public List<Board> boardList(Board board) {
        List<Board> boardList = (List<Board>) boardRepository.findAll();
        return  boardList;
    }

    @Override
    public void boardInsert(Board board) {
        boardRepository.save(board);
    }

    @Override
    public Board getBoard(Long no) {
        return boardRepository.findById(no)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 존재하지 않습니다."));
    }

    @Override
    public Board boardHitUpdate(Board board) {
        Board dataBoard = getBoard(board.getNo());
        dataBoard.setHit(dataBoard.getHit() + 1);
        boardRepository.save(dataBoard);
        return dataBoard;
    }

    @Override
    public Board boardDetail(Board board) {
        Board detail = boardHitUpdate(board);
        return detail;
    }

    @Override
    public void boardUpdate(Board board) {
        Optional<Board> boardOptional = boardRepository.findById(board.getNo());
        Board updateBoard = boardOptional.orElseThrow();
        updateBoard.setTitle(board.getTitle());
        updateBoard.setContent(board.getContent());

        boardRepository.save(updateBoard);
    }

    @Override
    public void boardDelete(Board board) {
        boardRepository.deleteById(board.getNo());
    }

}
