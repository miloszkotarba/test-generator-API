package com.mkotarba.first_crud_api.service;

import com.mkotarba.first_crud_api.collection.Exercise;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExerciseService {
    String save(Exercise exercise);

    List<Exercise> findAll();
}
