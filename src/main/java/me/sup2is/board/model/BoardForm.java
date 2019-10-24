package me.sup2is.board.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class BoardForm {
	
	private long bno;

	@NotBlank(message = "Title은 필수 항목입니다.")
	private String title;
	
	@NotBlank(message = "User ID는 필수 항목입니다.")
	private String userId;
	
	@NotBlank(message = "Contents는 필수 항목입니다.")
	private String contents;
	
	private Date reg_at;
	
	private Board board;

	public BoardForm(String title, String userId, String contents, Date reg_at) {
		this.title = title;
		this.userId = userId;
		this.contents = contents;
		this.reg_at = reg_at;
		
		this.board = Board.builder()
						.title(title)
						.userId(userId)
						.contents(contents)
						.reg_at(reg_at).build();
	}
}
