package me.sup2is.board.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.sup2is.board.exception.ExceptionController;
import me.sup2is.board.model.BoardForm;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class BoardControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private BoardController boardController;
	
	@InjectMocks
	private BoardService boardService = new BoardServiceImpl();
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(boardController)
				.setControllerAdvice(new ExceptionController())
				.addFilters(new CharacterEncodingFilter("UTF-8", true))
				.build();
	}
	
	@Test
	void boardController_write_test() throws JsonProcessingException, Exception {
		//given
		BoardForm boardForm = new BoardForm("첫 게시글입니다.", "sup2is", "안녕하세요", null);
		
		//when
		MvcResult result = mockMvc.perform(post("/board/write")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(boardForm)))
				.andDo(print())
				.andReturn();
		
		//then
		assertEquals(HttpStatus.OK, HttpStatus.valueOf(result.getResponse().getStatus()));
	}
	
	@Test
	void boardController_write_test_제목이없을때() throws JsonProcessingException, Exception {
		//given
		BoardForm boardForm = new BoardForm("", "sup2is", "안녕하세요", null);
		List<String> expectErrorMessage = new ArrayList<>();
		expectErrorMessage.add("Title은 필수 항목입니다.");
		
		//when
		MvcResult result = mockMvc.perform(post("/board/write")
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(boardForm)))
							.andDo(print())
							.andReturn();
		
		//then
		assertEquals(HttpStatus.BAD_REQUEST, HttpStatus.valueOf(result.getResponse().getStatus()));
		assertEquals(objectMapper.writeValueAsString(expectErrorMessage), result.getResponse().getContentAsString());
	}
	
	@Test
	public void objectMapper_writeValueAsString() throws JsonProcessingException {
		BoardForm boardForm = new BoardForm("", "sup2is", "", null);
		System.out.println(objectMapper.writeValueAsString(boardForm));
	}

}
