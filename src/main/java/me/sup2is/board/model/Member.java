package me.sup2is.board.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String userId;

    private String username;

    private String email;

    private String zipcode;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private List<Board> boardList = new ArrayList<>();

    public static Member createMember(String userId, String username, String email, String zipcode) {
        Member member = new Member();
        member.userId = userId;
        member.email = email;
        member.username = username;
        member.zipcode = zipcode;
        member.createdAt = LocalDateTime.now();
        member.modifiedAt = LocalDateTime.now();
        return member;
    }

}
