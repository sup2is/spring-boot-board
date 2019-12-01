package me.sup2is.board.member;

import me.sup2is.board.model.Member;
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
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    @Rollback(false)
    public void 유저저장() throws Exception {
        //given
        Member member = Member.createMember("user1", "choi", "sup2is@gmail.com", "seoul");
        Long insertedId = memberService.save(member);
        //when

        //then
        assertEquals(insertedId, member.getId());

    }

}