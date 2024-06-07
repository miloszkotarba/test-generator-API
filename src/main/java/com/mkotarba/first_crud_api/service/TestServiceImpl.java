package com.mkotarba.first_crud_api.service;

import com.mkotarba.first_crud_api.collection.Test;
import com.mkotarba.first_crud_api.respository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestRepository testRepository;

    @Override
    public Test save(Test test) {
        return testRepository.save(test);
    }

    @Override
    public List<Test> findAll() {
        return testRepository.findAll();
    }
}
