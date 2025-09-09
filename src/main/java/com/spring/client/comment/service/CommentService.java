package com.spring.client.comment.service;

import com.spring.client.comment.domain.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> commentList(Comment comment);
    public Comment commentInsert(Comment comment);
    public Comment commentUpdate(Comment comment);
    public void commentDelete(Comment comment);
}
