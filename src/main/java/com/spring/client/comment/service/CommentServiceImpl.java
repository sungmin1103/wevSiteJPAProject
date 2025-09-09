package com.spring.client.comment.service;

import com.spring.client.comment.domain.Comment;
import com.spring.client.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public List<Comment> commentList(Comment comment) {
        List<Comment> commentList = commentRepository.articleNoCommentList(comment.getArticle().getNo());
        return commentList;
    }

    @Override
    public Comment commentInsert(Comment comment) {
        Comment result = commentRepository.save(comment);
        return result;
    }

    @Override
    public Comment commentUpdate(Comment comment) {
        Optional<Comment> commentOptional = commentRepository.findById(comment.getId());
        Comment updateComment = commentOptional.get();
        updateComment.setBody(comment.getBody());
        Comment result = commentRepository.save(updateComment);
        return result;
    }

    @Override
    public void commentDelete(Comment comment) {
        commentRepository.deleteById(comment.getId());
    }
}
