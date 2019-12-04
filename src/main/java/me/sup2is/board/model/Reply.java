package me.sup2is.board.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Lob
    private String contents;

    private Integer level;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Reply> childReplies = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Reply parentReply;

    private static void addReply(Reply reply) {
        reply.getBoard().getReplyList().add(reply);
    }

    public static Reply createReply(Member member, Board board, String contents, Reply parentReply) {
        Reply reply = new Reply();
        reply.member = member;
        reply.board = board;
        reply.contents = contents;
        reply.parentReply = parentReply;
        reply.level = measureLevel(reply, 0);
        if(parentReply != null) {
            parentReply.getChildReplies().add(reply);
        }
        addReply(reply);
        return reply;
}

    private static int measureLevel(Reply reply, int level) {
        if(reply == null || level > 5) {
            return level;
        }
        return measureLevel(reply.parentReply, level + 1);
    }

    public boolean hasChild () {
        return this.childReplies.size() > 0 ? true : false;
    }

}
