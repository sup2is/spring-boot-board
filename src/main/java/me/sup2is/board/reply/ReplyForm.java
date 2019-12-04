package me.sup2is.board.reply;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ReplyForm {

    @NotBlank
    private String contents;

}
