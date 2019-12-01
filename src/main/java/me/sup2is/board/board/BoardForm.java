package me.sup2is.board.board;

import lombok.Getter;
import lombok.Setter;
import me.sup2is.board.model.Board;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class BoardForm {

    @NotBlank(message = "Title은 필수 항목입니다.")
    private String title;

    @NotBlank(message = "User ID는 필수 항목입니다.")
    private String userId;

    @NotBlank(message = "Contents는 필수 항목입니다.")
    private String contents;

}
