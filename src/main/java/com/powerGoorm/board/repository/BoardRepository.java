package com.powerGoorm.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.powerGoorm.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
