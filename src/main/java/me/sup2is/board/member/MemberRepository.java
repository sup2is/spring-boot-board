package me.sup2is.board.member;

import lombok.RequiredArgsConstructor;
import me.sup2is.board.model.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    final private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findOne(Long memberId) {
        return em.find(Member.class,memberId);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m").getResultList();
    }

    public void remove(Member member) {
        em.remove(member);
    }

    public List<Member> findByUserId(String userId) {
        TypedQuery<Member> query = em.createQuery("select m from Member m where m.userId = :userId", Member.class);
        return  query.setParameter("userId", userId)
                .getResultList();
    }
}
