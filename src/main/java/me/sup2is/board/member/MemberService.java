package me.sup2is.board.member;

import javassist.bytecode.DuplicateMemberException;
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

    public Long save(Member member) throws DuplicateMemberException {
        if(validateDuplicateUserId(member.getUserId())){
            return memberRepository.save(member);
        }else {
            throw new DuplicateMemberException("같은 이름의 사용자가 존재합니다");
        }
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public void updateMember(Long memberId, MemberForm memberForm) {
        Member findMember = findMemberById(memberId);

        findMember.updateMember(memberForm.getPasswd(),
                memberForm.getUsername(),
                memberForm.getEmail(),
                memberForm.getZipcode());
    }

    public void deleteMember(Long memberId) {
        memberRepository.remove(findMemberById(memberId));
    }

    public Member findMemberByUserId(String userId) {
        List<Member> findMembers = memberRepository.findByUserId(userId);
        return findMembers.size() > 0 ? findMembers.get(0) : null;
    }

    public boolean validateDuplicateUserId(String userId) {
        return findMemberByUserId(userId) == null ? true : false;
    }

}
