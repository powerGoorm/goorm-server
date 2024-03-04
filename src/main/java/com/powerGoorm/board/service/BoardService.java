package com.powerGoorm.board.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.powerGoorm.board.dto.BoardCreateRequest;
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
    public BoardResponse create(BoardCreateRequest request, String memberId) {
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
}
