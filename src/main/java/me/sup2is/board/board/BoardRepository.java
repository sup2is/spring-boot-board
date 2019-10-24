package me.sup2is.board.board;

import org.springframework.data.jpa.repository.JpaRepository;

import me.sup2is.board.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
}
