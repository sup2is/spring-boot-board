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
import java.util.List;

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

	@Test
	public void 로그인한유저의_글확인() throws Exception {
	    //given
		Member member = Member.createMember("sup2is", "password", "sup2is", "dev.sup2is@gmail.com", "읭?");
		memberRepository.save(member);
		Member another = Member.createMember("another", "password", "sup2is", "dev.sup2is@gmail.com", "읭?");
		memberRepository.save(another);
		Board board1 = Board.createBoard(member,"글 저장입니다1" , "글 저장입니다@@@");
		boardService.save(board1);
		Board board2 = Board.createBoard(member,"글 저장입니다2" , "글 저장입니다@@@");
		boardService.save(board2);
		Board board3 = Board.createBoard(member,"글 저장입니다3" , "글 저장입니다@@@");
		boardService.save(board3);
		Board board4 = Board.createBoard(another,"글 저장입니다4" , "글 저장입니다@@@");
		boardService.save(board4);
		Board board5 = Board.createBoard(another,"글 저장입니다5" , "글 저장입니다@@@");
		boardService.save(board5);

	    //when
		List<Board> allByMemberId = boardService.findAllByMemberId(member.getId());

		//then
		assertEquals(3, allByMemberId.size());
	}



}
