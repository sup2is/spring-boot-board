package me.sup2is.board.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bno;
	
	@Column(length = 500, nullable = false)
	private String title;
	
	@Column(name = "user_id", nullable = false)
	private String userId;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String contents;
	
	@Column(columnDefinition = "DATETIME")
	private Date reg_at;
}
