package me.sup2is.board.board;

import lombok.RequiredArgsConstructor;
import me.sup2is.board.member.MemberRepository;
import me.sup2is.board.model.Member;
import org.springframework.stereotype.Service;

import me.sup2is.board.model.Board;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

	final private BoardRepository boardRepository;

	public Long save(Board board) {
		return boardRepository.save(board);
	}

	public Board findBoardById(Long boardId) {
		return boardRepository.findOne(boardId);
	}

	public void updateBoard(Long boardId, BoardForm boardForm) {
		Board findBoard = findBoardById(boardId);
		findBoard.updateBoard(boardForm.getContents(), boardForm.getTitle());
	}

}
