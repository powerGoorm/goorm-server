package com.powerGoorm.board.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.powerGoorm.board.dto.BoardRequest;
import com.powerGoorm.board.dto.BoardListResponse;
import com.powerGoorm.board.dto.BoardResponse;
import com.powerGoorm.board.entity.Board;
import com.powerGoorm.board.repository.BoardRepository;
import com.powerGoorm.member.Member;
import com.powerGoorm.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public BoardResponse create(BoardRequest request, String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("로그인된 사용자 정보를 찾을 수 없습니다."));

        Board board = Board.builder()
                .title(request.title())
                .content(request.content())
                .member(member)
                .createdAt(LocalDateTime.now())
                .build();
        return BoardResponse.from(boardRepository.save(board));
    }

    public Page<BoardListResponse> findALl(Pageable pageable) {
        return boardRepository.findAll(pageable)
                .map(BoardListResponse::from);
    }

    public BoardResponse find(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        if (board.isDeleted()) {
            throw new IllegalArgumentException("삭제된 게시글입니다.");
        }
        return BoardResponse.from(board);
    }

    @Transactional
    public BoardResponse update(BoardRequest request, Long boardId, String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("로그인된 사용자 정보를 찾을 수 없습니다."));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        if (!member.getId().equals(board.getMember().getId())) {
            throw new IllegalArgumentException("게시글 작성자만 수정할 수 있습니다.");
        }

        board.update(request.title(), request.content());
        return BoardResponse.from(board);
    }
}
