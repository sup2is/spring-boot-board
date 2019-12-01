package me.sup2is.board.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private List<Board> boardList = new ArrayList<>();

}
