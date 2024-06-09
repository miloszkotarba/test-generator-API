package com.mkotarba.first_crud_api.controller;

import com.mkotarba.first_crud_api.collection.Test;
import com.mkotarba.first_crud_api.service.PDFGeneratorService;
import com.mkotarba.first_crud_api.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/test")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Test", description = "Endpoint for managing tests.")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private PDFGeneratorService pdfGeneratorService;

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

    @Operation(summary = "Get test by id", description = "Get test by id.")
    @ApiResponse(responseCode = "200", description = "Test found.")
    @ApiResponse(responseCode = "404", description = "Test not found.")
    @GetMapping("/{id}")
    public ResponseEntity<?> getTestById(@PathVariable String id) {
        Optional<Test> test = testService.findById(id);

        if (test.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test not found.");
        } else {
            return ResponseEntity.ok(test);
        }
    }

    @Operation(summary = "Add exercise to test", description = "Add exercise to test.")
    @ApiResponse(responseCode = "200", description = "Exercise added to test.")
    @ApiResponse(responseCode = "404", description = "Test or Exercise not found.")
    @PostMapping("/{id}/exercise")
    public ResponseEntity<?> addExerciseToTest(@PathVariable String id, @RequestBody Map<String, String> request) {
        String exerciseId = request.get("exerciseId");
        if (exerciseId == null) {
            return ResponseEntity.badRequest().body("Exercise ID is required.");
        }

        Test updatedTest = testService.addExerciseToTest(id, exerciseId);

        if (updatedTest == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test or Exercise not found.");
        }
        return ResponseEntity.ok(updatedTest);
    }

    @Operation(summary = "Delete a test", description = "Delete a test from the database.")
    @ApiResponse(responseCode = "200", description = "Test deleted.")
    @ApiResponse(responseCode = "404", description = "Test not found.")
    @DeleteMapping("/{id}")
    public HttpEntity<String> delete(@PathVariable String id) {
        boolean deleted = testService.deleteById(id);

        if (deleted) {
            return ResponseEntity.ok("Test deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test not found.");
        }
    }

    @Operation(summary = "Delete exercise from test", description = "Delete exercise from test.")
    @ApiResponse(responseCode = "200", description = "Exercise deleted from test.")
    @ApiResponse(responseCode = "404", description = "Test or Exercise not found.")
    @DeleteMapping("/{id}/exercise/{exerciseId}")
    public ResponseEntity<?> deleteExerciseFromTest(@PathVariable String id, @PathVariable String exerciseId) {
        Test updatedTest = testService.deleteExerciseFromTest(id, exerciseId);

        if (updatedTest == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test or Exercise not found.");
        }
        return ResponseEntity.ok(updatedTest);
    }

    @Operation(summary = "Update test", description = "Update test in the database.")
    @ApiResponse(responseCode = "200", description = "Test updated.")
    @ApiResponse(responseCode = "404", description = "Test not found.")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTest(@PathVariable String id, @RequestBody @Valid Test test) {
        Optional<Test> updatedTest = testService.updateTest(id, test);

        if (updatedTest.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test not found.");
        } else {
            return ResponseEntity.ok(updatedTest);
        }
    }

    // Endpoint do generowania PDF
    @Operation(summary = "Generate PDF", description = "Generate PDF.")
    @ApiResponse(responseCode = "200", description = "PDF generated.")
    @ApiResponse(responseCode = "404", description = "PDF not found.")
    @GetMapping("{id}/pdf")
    public ResponseEntity<?> generatePDF(@PathVariable String id, HttpServletResponse response) {
        try {
            Optional<Test> testOptional = testService.findById(id);

            if (testOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test not found. Could not create PDF Document.");
            }

            Test test = testOptional.get();

            response.setContentType("application/pdf");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=test_" + currentDateTime + ".pdf";
            response.setHeader(headerKey, headerValue);

            pdfGeneratorService.export(response, test);

            return ResponseEntity.ok("PDF generated.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate PDF.");
        }
    }

}
