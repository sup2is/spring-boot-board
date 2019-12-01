package me.sup2is.board.member;

import me.sup2is.board.model.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void 유저저장() throws Exception {
        //given
        Member member = Member.createMember("user1", "choi", "sup2is@gmail.com", "seoul");
        Long insertedId = memberService.save(member);
        //when

        //then
        assertEquals(insertedId, member.getId());
    }

    @Test
    public void 유저목록() throws Exception {
        //given
        Member member1 = Member.createMember("user1", "choi", "sup2is@gmail.com", "seoul");
        Member member2 = Member.createMember("user2", "choi", "sup2is@gmail.com", "seoul");
        memberService.save(member1);
        memberService.save(member2);

        //when
        List<Member> list = memberService.findMembers();

        //then
        assertEquals(list.size(), 2);
    }







}