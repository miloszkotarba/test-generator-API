package com.mkotarba.first_crud_api.service;

import com.mkotarba.first_crud_api.collection.Comment;
import com.mkotarba.first_crud_api.collection.Exercise;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ExerciseService {
    Exercise save(Exercise exercise);

    List<Exercise> findAll();

    Optional<Exercise> findById(String id);

    Exercise addCommentToExercise(String id, Comment comment);

    boolean deleteById(String id);

    Optional<Exercise> updateExercise(String id, Exercise exercise);
}
