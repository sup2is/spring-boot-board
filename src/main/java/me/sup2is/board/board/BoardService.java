package me.sup2is.board.board;

import lombok.RequiredArgsConstructor;
import me.sup2is.board.member.MemberService;
import me.sup2is.board.model.Board;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

	final private BoardRepository boardRepository;
	final private MemberService memberService;

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

	public void deleteBoard(Long boardId) {
		boardRepository.remove(findBoardById(boardId));
	}

	public List<Board> findAllByMemberId(Long memberId) {
		return boardRepository.findAllByMemberId(memberService.findMemberById(memberId));
	}
}
