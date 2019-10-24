package me.sup2is.board.board;

import org.springframework.stereotype.Service;

import me.sup2is.board.model.Board;

@Service
public interface BoardService {
	
	public void write(Board board);
	
}
