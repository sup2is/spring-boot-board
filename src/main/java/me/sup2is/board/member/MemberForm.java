package me.sup2is.board.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MemberForm {

    @NotNull(message = "패스워드를 입력해주세요.")
    private String passwd;

    @NotNull(message = "이름을 입력해주세요")
    private String username;

    @Email(message = "이메일 형식이 아닙니다. 이메일을 확인해주세요.")
    @NotNull(message = "이메일을 입력해주세요.")
    private String email;

    private String zipcode;

}
