package com.mkotarba.first_crud_api.service;

import com.mkotarba.first_crud_api.collection.Test;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TestService {
    Test save(Test test);

    List<Test> findAll();

    Optional<Test> findById(String id);

    Test addExerciseToTest(String id, String exercise);

    boolean deleteById(String id);

    Test deleteExerciseFromTest(String id, String exerciseId);

    Optional<Test> updateTest(String id, Test test);
}
