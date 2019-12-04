package me.sup2is.board.reply;

import lombok.RequiredArgsConstructor;
import me.sup2is.board.model.Reply;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {

    final private EntityManager em;

    public Long save(Reply reply) {
        em.persist(reply);
        return reply.getId();
    }

    public Reply findOne(Long replyId) {
        return em.find(Reply.class, replyId);
    }
}
