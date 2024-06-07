package com.mkotarba.first_crud_api.service;

import com.mkotarba.first_crud_api.collection.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    Comment save(Comment comment);
    List<Comment> findAll();

    Comment findById(String id);

    boolean deleteById(String id);
}
