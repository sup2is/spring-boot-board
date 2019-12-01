package me.sup2is.board.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.*;

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
}
