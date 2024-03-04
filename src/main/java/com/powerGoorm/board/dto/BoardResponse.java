package com.powerGoorm.board.dto;

import java.time.LocalDateTime;

import com.powerGoorm.board.entity.Board;

public record BoardResponse(
        Long id,
        String memberId,
        String memberName,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static BoardResponse from(Board board) {
        return new BoardResponse(
                board.getId(),
                board.getMember().getId(),
                board.getMember().getName(),
                board.getTitle(),
                board.getContent(),
                board.getCreatedAt(),
                board.getUpdatedAt());
    }
}
