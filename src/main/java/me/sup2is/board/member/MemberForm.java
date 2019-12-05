package me.sup2is.board.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.sup2is.board.model.Member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class MemberForm {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;

    @NotBlank(message = "패스워드를 입력해주세요.")
    private String passwd;

    @NotBlank(message = "이름을 입력해주세요")
    private String username;

    @Email(message = "이메일 형식이 아닙니다. 이메일을 확인해주세요.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    private String zipcode;

    public Member createMember() {
        return Member.createMember(userId, passwd, username, email, zipcode);
    }
}
