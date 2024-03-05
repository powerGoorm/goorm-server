package com.powerGoorm.board.dto;

import java.time.LocalDateTime;

import com.powerGoorm.board.entity.Board;

public record BoardListResponse(
        Long id,
        String memberId,
        String memberName,
        String title,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static BoardListResponse from(Board board) {
        return new BoardListResponse(
                board.getId(),
                board.getMember().getId(),
                board.getMember().getName(),
                board.getTitle(),
                board.getCreatedAt(),
                board.getUpdatedAt());
    }
}
