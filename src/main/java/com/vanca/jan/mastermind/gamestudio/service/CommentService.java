package com.vanca.jan.mastermind.gamestudio.service;

import com.vanca.jan.mastermind.gamestudio.entity.Comment;
import com.vanca.jan.mastermind.gamestudio.service.exception.CommentException;

import java.util.List;

public interface CommentService {
    void addComment(Comment comment) throws CommentException;
    List<Comment> getComments(String game) throws CommentException;
}