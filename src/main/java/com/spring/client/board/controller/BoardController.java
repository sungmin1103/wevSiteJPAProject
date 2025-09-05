package com.spring.client.board.controller;

import com.spring.client.common.dto.PageRequestDTO;
import com.spring.client.common.dto.PageResponseDTO;
import com.spring.client.board.domain.Board;
import com.spring.client.board.service.BoardService;
import com.spring.client.common.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
    public final BoardService boardService;
    private final CustomFileUtil fileUtil; /* 필드 선언 */

    /**
     * 검색 기능 및 페이징 처리 제외
     * @param board
     * @return
     */
   /* @GetMapping("/boardList")
    public String boardList(Board board, Model model) {
        List<Board> boardList = boardService.boardList(board);
        model.addAttribute("boardList", boardList);

        return "client/board/boardList";
    }*/

    @GetMapping("/boardList")
    public String boardList(Board board, PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<Board> boardList = boardService.list(pageRequestDTO);
        model.addAttribute("boardList", boardList);

        return "client/board/boardList";
    }

    @GetMapping("/insertForm")
    public String insertForm(Board board) {
        return "client/board/insertForm";
    }
/* 게시글만 입력
    @PostMapping("/boardInsert")
    public String boardList(Board board) {
        boardService.boardInsert(board);
        return "redirect:/board/boardList";
    }
 */

    @PostMapping("/boardInsert")
    public String boardInsert(Board board) {
        if (!board.getFile().isEmpty()) { // 새로 업로드 파일이 존재하면
            String uploadFileName = fileUtil.saveFile(board.getFile());
            board.setFilename(uploadFileName);
        }
        boardService.boardInsert(board);
        return "redirect:/board/boardList";
    }

    @GetMapping("/{no}")
    public String boardDetail(@PathVariable Long no, Board board, Model model) {
        board.setNo(no);
        Board detail = boardService.boardDetail(board);
        model.addAttribute("detail", detail);

        /*String에서 줄바꿈(newLine)은 Window에서 \r\n, Linux에서 \n으로 표현된다.
        * 하지만 이런방식은, 서로 다른 종유의 OS에서 동작하는 프로그램에서 문제가 발생할 수 있다.
        * [참고]String#format()의 %n: String#format()에서 %n은 line separator를 의미한다.
        */
        String newLine = System.getProperty("line.separator").toString();
        model.addAttribute("newLine", newLine);

        return "client/board/boardDetail";
    }

    @PostMapping("/updateForm")
    public String updateForm(Board board, Model model) {
        Board updateData = boardService.getBoard(board.getNo());
        model.addAttribute("updateData", updateData);
        return "client/board/updateForm";
    }

   /* @PostMapping("/boardUpdate")
    public String boardUpdate(Board board) {
        boardService.boardUpdate(board);
        return "redirect:/board/"+board.getNo();
    }*/

    @PostMapping("/boardUpdate")
    public String boardUpdate(Board board) {
        Board updateData = boardService.getBoard(board.getNo());

        if(!board.getFile().isEmpty()) { // 새로 업로드 파일이 존재하면
            if(updateData.getFilename()!= null) {   // 기존 파일이 존재하면
                fileUtil.deleteFile(updateData.getFilename());
            }

            String uploadFileName = fileUtil.saveFile(board.getFile());
            board.setFilename(uploadFileName);
        }

        boardService.boardUpdate(board);
        return "redirect:/board/"+board.getNo();
    }

//    @PostMapping("/boardDelete")
//    public String boardDelete(Board board) {
//        boardService.boardDelete(board);
//        return  "redirect:/board/boardList";
//    }

    @PostMapping("/boardDelete")
    public String boardDelete(Board board) {
        Board deleteData = boardService.getBoard(board.getNo());
        if(deleteData.getFilename()!= null) {   // 기존 파일이 존재하면
            fileUtil.deleteFile(deleteData.getFilename());
        }
        boardService.boardDelete(board);
        return "redirect:/board/boardList";
    }

    @ResponseBody
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName) {
        return fileUtil.getFile(fileName);
    }
}
