package me.sup2is.board.reply;

import lombok.RequiredArgsConstructor;
import me.sup2is.board.exception.ReplyHasChildException;
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

    public void deleteReply(Long replyId) throws ReplyHasChildException {
        Reply findReply = findByReplyId(replyId);
        if(!findReply.hasChild()){
            replyRepository.remove(findReply);
        }else {
            throw new ReplyHasChildException("이 댓글은 하위 댓글이 있기때문에 삭제가 불가능합니다.");
        }
    }
}
