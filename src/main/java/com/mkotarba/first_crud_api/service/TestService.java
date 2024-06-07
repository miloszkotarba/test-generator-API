package com.mkotarba.first_crud_api.service;

import com.mkotarba.first_crud_api.collection.Test;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestService {
    Test save(Test test);

    List<Test> findAll();
}
