package com.mkotarba.first_crud_api.respository;

import com.mkotarba.first_crud_api.collection.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends MongoRepository<Exercise, String> {
}
