package me.sup2is.board.member;

import lombok.RequiredArgsConstructor;
import me.sup2is.board.model.Member;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    final private MemberRepository memberRepository;

    public Long save(Member member) {
        return memberRepository.save(member);
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public void updateMember(Member member, MemberForm memberForm) {
        Member findMember = findMemberById(member.getId());

        findMember.updateMember(memberForm.getPasswd(),
                memberForm.getUsername(),
                memberForm.getEmail(),
                memberForm.getZipcode());
    }
}
