package com.powerGoorm.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.powerGoorm.board.dto.BoardRequest;
import com.powerGoorm.board.dto.BoardListResponse;
import com.powerGoorm.board.dto.BoardResponse;
import com.powerGoorm.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board")
    public BoardResponse createBoard(@RequestBody BoardRequest createRequest,
            @SessionAttribute("id") String memberId) {
        return boardService.create(createRequest, memberId);
    }

    @GetMapping("/board")
    public Page<BoardListResponse> findALlBoard(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return boardService.findALl(pageable);
    }

    @GetMapping("/board/{boardId}")
    public BoardResponse getBoard(@PathVariable("boardId") Long id) {
        return boardService.find(id);
    }

    @PutMapping("/board/{boardId}")
    public BoardResponse update(@RequestBody BoardRequest request,
            @PathVariable("boardId") Long boardId,
            @SessionAttribute("id") String memberId) {
        return boardService.update(request, boardId, memberId);
    }

    @DeleteMapping("/board/{boardId}")
    public void delete(@PathVariable("boardId") Long boardId,
            @SessionAttribute("id") String memberId) {
        boardService.delete(boardId, memberId);
    }
}
