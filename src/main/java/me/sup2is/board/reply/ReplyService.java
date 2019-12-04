package me.sup2is.board.reply;

import lombok.RequiredArgsConstructor;
import me.sup2is.board.model.Reply;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyService {

    final private ReplyRepository replyRepository;

    public Long save(Reply reply) {
        return replyRepository.save(reply);
    }

    public Reply findByReplyId(Long replyId) {
        return replyRepository.findOne(replyId);
    }
}
