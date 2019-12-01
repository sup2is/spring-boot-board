package me.sup2is.board.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private long id;

	private String title;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@Lob
	private String contents;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "reply_id")
	private List<Reply> replyList = new ArrayList<>();

	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;

	public static Board createBoard(Member member, String title, String contents) {
		Board board = new Board();
		board.member = member;
		board.title = title;
		board.contents = contents;
		board.createdAt = LocalDateTime.now();
		board.modifiedAt = LocalDateTime.now();
		return board;
	}

	public void updateBoard(String contents, String title) {
		this.contents = contents;
		this.title = title;
	}

}
