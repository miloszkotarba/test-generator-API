package com.mkotarba.first_crud_api.service;

import com.mkotarba.first_crud_api.collection.Exercise;
import com.mkotarba.first_crud_api.respository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public String save(Exercise exercise) {
        return exerciseRepository.save(exercise).getExerciseId();
    }
}
