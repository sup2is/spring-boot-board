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
        Member member = Member.createMember("user1" , "password" , "choi", "sup2is@gmail.com", "seoul");
        Long insertedId = memberService.save(member);
        //when

        //then
        assertEquals(insertedId, member.getId());
    }

    @Test
    public void 유저목록() throws Exception {
        //given
        Member member1 = Member.createMember("user1", "password", "choi", "sup2is@gmail.com", "seoul");
        Member member2 = Member.createMember("user2", "password", "choi", "sup2is@gmail.com", "seoul");
        memberService.save(member1);
        memberService.save(member2);

        //when
        List<Member> list = memberService.findMembers();

        //then
        assertEquals(list.size(), 2);
    }

    @Test
    @Rollback(false)
    public void 유저수정() throws Exception {
        //given
        Member member = Member.createMember("user1", "password", "choi", "sup2is@gmail.com", "seoul");
        memberService.save(member);

        MemberForm memberForm = new MemberForm();
        memberForm.setEmail("변경된 이메일");
        memberForm.setPasswd("변경된 패스워드");
        memberForm.setUsername("변경된 이름");
        memberForm.setZipcode("변경된 집코드");

        //when
        Member findMember = memberService.findMemberById(member.getId());
        memberService.updateMember(findMember, memberForm);

        //then
        Member updateMember = memberService.findMemberById(member.getId());
        assertEquals(updateMember.getUsername() , memberForm.getUsername());
        assertEquals(updateMember.getPasswd() , memberForm.getPasswd());
        assertEquals(updateMember.getEmail() , memberForm.getEmail());
        assertEquals(updateMember.getZipcode() , memberForm.getZipcode());
    }
}