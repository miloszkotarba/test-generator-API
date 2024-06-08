package com.mkotarba.first_crud_api.service;

import com.mkotarba.first_crud_api.collection.Category;
import com.mkotarba.first_crud_api.collection.Exercise;
import com.mkotarba.first_crud_api.collection.Test;
import com.mkotarba.first_crud_api.respository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private ExerciseService exerciseService;

    @Override
    public Test save(Test test) {
        return testRepository.save(test);
    }

    @Override
    public List<Test> findAll() {
        return testRepository.findAll();
    }

    @Override
    public Optional<Test> findById(String id) {
        return testRepository.findById(id);
    }

    @Override
    public Test addExerciseToTest(String id, String exerciseId) {
        Optional<Exercise> optionalExercise = exerciseService.findById(exerciseId);

        if (optionalExercise.isEmpty()) {
            return null;
        }

        Exercise exercise = optionalExercise.get();

        return testRepository.findById(id)
                .map(test -> {
                    test.getExercises().add(exercise);
                    return testRepository.save(test);
                })
                .orElse(null);
    }

    @Override
    public boolean deleteById(String id) {
        Optional<Test> optionalTest = findById(id);
        if (optionalTest.isPresent()) {
            testRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Test deleteExerciseFromTest(String id, String exerciseId) {
        return testRepository.findById(id)
                .map(test -> {
                    test.getExercises().removeIf(exercise -> exercise.getExerciseId().equals(exerciseId));
                    return testRepository.save(test);
                })
                .orElse(null);
    }

    @Override
    public Optional<Test> updateTest(String id, Test test) {
        return testRepository.findById(id)
                .map(existingTest -> {
                    existingTest.setName(test.getName());
                    existingTest.setDuration(test.getDuration());
                    return testRepository.save(existingTest);
                });
    }
}
