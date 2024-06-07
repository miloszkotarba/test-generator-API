package com.mkotarba.first_crud_api.controller;

import com.mkotarba.first_crud_api.collection.Comment;
import com.mkotarba.first_crud_api.collection.Exercise;
import com.mkotarba.first_crud_api.service.CommentService;
import com.mkotarba.first_crud_api.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/exercise")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Exercise", description = "Endpoint for managing exercises.")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;


    @Operation(summary = "Create a new exercise", description = "Create a new exercise in the database. Returns the id of the created exercise.")
    @PostMapping
    public String save(@RequestBody @Valid Exercise exercise) {
        return exerciseService.save(exercise);
    }

    @Operation(summary = "Get all exercises", description = "Get all exercises.")
    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.findAll();
        return ResponseEntity.ok(exercises);
    }

    @Operation(summary = "Add comment to exercise", description = "Add comment to exercise.")
    @PostMapping("/{id}/comment")
    public ResponseEntity<?> addComment(@PathVariable String id, @RequestBody @Validated Comment comment) {
        Exercise updatedExercise = exerciseService.addCommentToExercise(id, comment);

        if (updatedExercise == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise not found.");
        }
        return ResponseEntity.ok(updatedExercise);
    }

    @Operation(summary = "Delete exercise", description = "Delete exercise from the database and all comments associated with it.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExercise(@PathVariable String id) {
        boolean deleted = exerciseService.deleteById(id);

        if (deleted) {
            return ResponseEntity.ok("Exercise deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise not found.");
        }
    }
}
