package com.mkotarba.first_crud_api.controller;

import com.mkotarba.first_crud_api.collection.Exercise;
import com.mkotarba.first_crud_api.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/exercise")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Exercise", description = "Endpoint for managing exercises.")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;


    @Operation(summary = "Create a new exercise", description = "Create a new exercise in the database. Returns the id of the created exercise.")

    @PostMapping
    public String save(@RequestBody Exercise exercise) {
        return exerciseService.save(exercise);
    }

    @Operation(summary = "Get all exercises", description = "Get all exercises.")
    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.findAll();
        return ResponseEntity.ok(exercises);
    }
}
