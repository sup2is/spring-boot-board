package me.sup2is.board.member;

import lombok.RequiredArgsConstructor;
import me.sup2is.board.model.Member;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    final private MemberRepository memberRepository;

    public Long save(Member member) {
        return memberRepository.save(member);
    }

}
