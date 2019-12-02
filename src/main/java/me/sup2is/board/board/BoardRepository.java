package me.sup2is.board.board;

import lombok.RequiredArgsConstructor;
import me.sup2is.board.model.Board;
import me.sup2is.board.model.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository{

    final EntityManager em;

    public Long save(Board board) {
        em.persist(board);
        return board.getId();
    }

    public Board findOne(Long boardId) {
        return em.find(Board.class, boardId);
    }

    public void remove(Board board) {
        em.remove(board);
    }

    public List<Board> findAllByMemberId(Member member) {
        TypedQuery<Board> query = em.createQuery("select b from Board b where b.member = :member", Board.class);
        return query.setParameter("member", member)
                .getResultList();
    }
}
