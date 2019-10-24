package me.sup2is.board.board;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import me.sup2is.board.model.Board;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTests {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardRepository boardRepository;

	@Test
	public void 게시글저장() {
		Board board = Board.builder().title("spring boot & jpa로 작성하는 게시판")
									.contents("spring boot 좋아요")
									.userId("sup2is").build();
		boardService.write(board);
		assertTrue(boardRepository.findAll().size() > 0);
	}
}
