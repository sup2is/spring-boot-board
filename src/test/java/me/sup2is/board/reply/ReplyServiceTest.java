package me.sup2is.board.reply;

import me.sup2is.board.board.BoardService;
import me.sup2is.board.exception.ReplyHasChildException;
import me.sup2is.board.member.MemberService;
import me.sup2is.board.model.Board;
import me.sup2is.board.model.Member;
import me.sup2is.board.model.Reply;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberService memberService;

    @Test
    @Rollback(false)
    public void 댓글저장() throws Exception {
        //given
        Member member = Member.createMember("sup2is", "password", "sup2is", "dev.sup2is@gmail.com", "읭?");
        memberService.save(member);
        Board board = Board.createBoard(member,"글 저장입니다" , "글 저장입니다@@@");
        boardService.save(board);

        Reply reply = Reply.createReply(member, board, "첫댓 ..ㅜㅜ", null); //초기 레벨 1레벨의 경우

        //when
        replyService.save(reply);

        //then
        assertEquals(reply, replyService.findByReplyId(reply.getId()));
    }

    @Test
    @Rollback(false)
    public void 대댓글() throws Exception {
        //given
        Member member = Member.createMember("sup2is", "password", "sup2is", "dev.sup2is@gmail.com", "읭?");
        memberService.save(member);
        Board board = Board.createBoard(member,"글 저장입니다" , "글 저장입니다@@@");
        boardService.save(board);
        Reply reply = Reply.createReply(member, board, "첫댓 ..ㅜㅜ", null); //초기 레벨 1레벨의 경우
        replyService.save(reply);

        //when
        Reply childReply = Reply.createReply(member, board, "대댓...ㅜㅜ", reply);
        replyService.save(childReply);
        Reply thirdReply = Reply.createReply(member, board, "대대댓...ㅜㅜ", childReply);
        replyService.save(thirdReply);

        //then
        assertEquals(2, childReply.getLevel());
        assertEquals(3, thirdReply.getLevel());

        assertEquals(1, reply.getChildReplies().size());
        assertEquals(1, childReply.getChildReplies().size());

        Board findBoard = boardService.findBoardById(board.getId());
        assertEquals(3, findBoard.getReplyList().size());

    }

    @Test
    public void 댓글삭제 () throws Exception, ReplyHasChildException {
        //given
        Member member = Member.createMember("sup2is", "password", "sup2is", "dev.sup2is@gmail.com", "읭?");
        memberService.save(member);
        Board board = Board.createBoard(member,"글 저장입니다" , "글 저장입니다@@@");
        boardService.save(board);
        Reply reply = Reply.createReply(member, board, "첫댓 ..ㅜㅜ", null); //초기 레벨 1레벨의 경우
        replyService.save(reply);

        //when
        replyService.deleteReply(reply.getId());

        //then
        assertNull(replyService.findByReplyId(reply.getId()));
    }

    @Test(expected = ReplyHasChildException.class)
    public void 하위댓글이_있는_댓글_삭제 () throws Exception, ReplyHasChildException {
        //given
        Member member = Member.createMember("sup2is", "password", "sup2is", "dev.sup2is@gmail.com", "읭?");
        memberService.save(member);
        Board board = Board.createBoard(member,"글 저장입니다" , "글 저장입니다@@@");
        boardService.save(board);
        Reply reply = Reply.createReply(member, board, "첫댓 ..ㅜㅜ", null); //초기 레벨 1레벨의 경우
        replyService.save(reply);
        Reply childReply = Reply.createReply(member, board, "대댓...ㅜㅜ", reply);
        replyService.save(childReply);

        //when
        replyService.deleteReply(reply.getId());

        //then
        fail();

    }




}