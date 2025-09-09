package com.spring.client.comment.controller;

import com.spring.client.article.domain.Article;
import com.spring.client.comment.domain.Comment;
import com.spring.client.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/all/{no}")
    public List<Comment> commentList(@PathVariable Long no, Comment comment, Article article) {
        article.setNo(no);
        comment.setArticle(article);
        List<Comment> commentList = commentService.commentList(comment);
        return commentList;
    }

    @PostMapping(value = "/commentInsert", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Comment commentInsert(@RequestBody Comment comment) {
        Comment result = commentService.commentInsert(comment);
        return result;
    }

    @PostMapping(value = "/{id}", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Comment commentUpdate(@PathVariable Long id, @RequestBody Comment comment, Article article) {
        comment.setId(id);
        Comment result = commentService.commentUpdate(comment);
        return result;
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public void commentDelete(@PathVariable Long id, Comment comment) {
        comment.setId(id);
        commentService.commentDelete(comment);
    }



}
