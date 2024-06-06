package com.mkotarba.first_crud_api.controller;

import com.mkotarba.first_crud_api.collection.Comment;
import com.mkotarba.first_crud_api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Comment", description = "Endpoint for managing comments in the question.")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Operation(summary = "Get all comments", description = "Get all comments.")
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.findAll();

        return ResponseEntity.ok(comments);
    }

    @Operation(summary = "Get comment by id", description = "Get comment by id.")
    @ApiResponse(responseCode = "200", description = "Comment found.")
    @ApiResponse(responseCode = "404", description = "Comment not found.")
    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable String id) {
        Comment comment = commentService.findById(id);

        if (comment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found.");
        } else {
            return ResponseEntity.ok(comment);
        }
    }

    @Operation(summary = "Create a comment", description = "Create a comment.")
    @ApiResponse(responseCode = "201", description = "Comment created successfully. Returns created comment.")
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody @Validated Comment comment) {
        Comment createdComment = commentService.save(comment);

        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a comment", description = "Delete a comment from the database.")
    @ApiResponse(responseCode = "200", description = "Comment deleted.")
    @ApiResponse(responseCode = "404", description = "Commend not found.")
    @DeleteMapping("/{id}")
    public HttpEntity<String> delete(@PathVariable String id) {
        boolean deleted = commentService.deleteById(id);

        if (deleted) {
            return ResponseEntity.ok("Commend deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Commend not found.");
        }
    }

}
