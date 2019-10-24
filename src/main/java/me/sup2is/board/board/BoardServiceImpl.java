package me.sup2is.board.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.sup2is.board.model.Board;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Override
	public void write(Board board) {
		boardRepository.save(board);
	}

}
