package com.mkotarba.first_crud_api.service;

import com.mkotarba.first_crud_api.collection.Comment;
import com.mkotarba.first_crud_api.collection.Exercise;
import com.mkotarba.first_crud_api.respository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private CommentService commentService;

    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public Optional<Exercise> findById(String id) {
        return exerciseRepository.findById(id);
    }

    @Override
    public Exercise addCommentToExercise(String id, Comment comment) {
        return exerciseRepository.findById(id)
                .map(exercise -> {
                    Comment createdComment = commentService.save(comment);
                    exercise.getComments().add(createdComment);
                    return exerciseRepository.save(exercise);
                })
                .orElse(null);
    }

    @Override
    public boolean deleteById(String id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);

        if (exercise.isPresent()) {
            Exercise foundExercise = exercise.get();
            foundExercise.getComments().forEach(comment -> commentService.deleteById(comment.getCommentId()));
            exerciseRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public String save(Exercise exercise) {
        return exerciseRepository.save(exercise).getExerciseId();
    }
}
