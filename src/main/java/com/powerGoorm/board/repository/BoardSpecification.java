package com.powerGoorm.board.repository;

import org.springframework.data.jpa.domain.Specification;

import com.powerGoorm.board.entity.Board;

public class BoardSpecification {
    public static Specification<Board> notRemoved() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get("deletedAt"));
    }
}
