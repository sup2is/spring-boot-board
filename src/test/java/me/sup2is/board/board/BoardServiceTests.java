package me.sup2is.board.board;

import me.sup2is.board.member.MemberRepository;
import me.sup2is.board.model.Board;
import me.sup2is.board.model.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTests {

	@Autowired
	private BoardService boardService;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private EntityManager em;

	@Test
	@Rollback(false)
	public void 게시글저장() {

		//given
		Member member = Member.createMember("sup2is", "password", "sup2is", "dev.sup2is@gmail.com", "읭?");
		memberRepository.save(member);
		Board board = Board.createBoard(member,"글 저장입니다" , "글 저장입니다@@@");

		Long insertedId = boardService.save(board);

		//then
		assertEquals(insertedId, board.getId());
	}

	@Test
	public void 게시글수정() {
		//given
		Member member = Member.createMember("sup2is", "password", "sup2is", "dev.sup2is@gmail.com", "읭?");
		memberRepository.save(member);
		Board board = Board.createBoard(member,"글 저장입니다" , "글 저장입니다@@@");
		boardService.save(board);

		BoardForm boardForm = new BoardForm();
		boardForm.setContents("수정된 글입니다@@@@");
		boardForm.setTitle("수정된 글입니다");

		//when
		boardService.updateBoard(board.getId(), boardForm);

		//then
		Board updateBoard = boardService.findBoardById(board.getId());
		assertEquals(boardForm.getContents(), updateBoard.getContents());
		assertEquals(boardForm.getTitle(), updateBoard.getTitle());

	}

	@Test
	public void 게시글삭제() {
		//given
		Member member = Member.createMember("sup2is", "password", "sup2is", "dev.sup2is@gmail.com", "읭?");
		memberRepository.save(member);
		Board board = Board.createBoard(member,"글 저장입니다" , "글 저장입니다@@@");
		boardService.save(board);

		//when
		boardService.deleteBoard(board.getId());

		//then
		assertEquals(null, boardService.findBoardById(board.getId()));

	}
}
