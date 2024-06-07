package com.mkotarba.first_crud_api.controller;

import com.mkotarba.first_crud_api.collection.Comment;
import com.mkotarba.first_crud_api.collection.Exercise;
import com.mkotarba.first_crud_api.collection.Test;
import com.mkotarba.first_crud_api.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Test", description = "Endpoint for managing tests.")
public class TestController {

    @Autowired
    private TestService testService;

    @Operation(summary = "Create a test", description = "Create a test.")
    @ApiResponse(responseCode = "201", description = "Test created successfully. Returns created test.")
    @PostMapping
    public ResponseEntity<Test> createTest(@RequestBody @Valid Test test) {
        Test createdTest = testService.save(test);

        return new ResponseEntity<>(createdTest, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all tests", description = "Get all tests.")
    @GetMapping
    public ResponseEntity<List<Test>> getAllTests() {
        List<Test> tests = testService.findAll();
        return ResponseEntity.ok(tests);
    }

}
