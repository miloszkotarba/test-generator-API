package com.mkotarba.first_crud_api.controller;

import com.mkotarba.first_crud_api.collection.Category;
import com.mkotarba.first_crud_api.collection.CategoryDto;
import com.mkotarba.first_crud_api.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Category", description = "Endpoint for managing categories.")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Get all categories", description = "Get all categories.")
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();

        return ResponseEntity.ok(categories);
    }

    @Operation(summary = "Get category by id", description = "Get category by id.")
    @ApiResponse(responseCode = "200", description = "Found category.")
    @ApiResponse(responseCode = "404", description = "Category not found.")
    @GetMapping("/{id}")
    public HttpEntity<?> getCategoryById(@PathVariable String id) {
        Category category = categoryService.findById(id);

        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        } else {
            return ResponseEntity.ok(category);
        }
    }

    @Operation(summary = "Create a new category", description = "Create a new category in the database. Returns created category.")
    @ApiResponse(responseCode = "201", description = "Category created.")
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody @Valid Category category) {
        Category createdCategory = categoryService.save(category);

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a category", description = "Update a category in the database.")
    @ApiResponse(responseCode = "200", description = "Category updated.")
    @ApiResponse(responseCode = "404", description = "Category not found.")
    @PatchMapping("/{id}")
    public HttpEntity<String> update(@PathVariable String id, @RequestBody CategoryDto categoryDto) {
        boolean updated = categoryService.updateById(id, categoryDto);
        if (updated) {
            return ResponseEntity.ok("Category updated.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }
    }

    @Operation(summary = "Delete a category", description = "Delete a category from the database.")
    @ApiResponse(responseCode = "200", description = "Category deleted.")
    @ApiResponse(responseCode = "404", description = "Category not found.")
    @DeleteMapping("/{id}")
    public HttpEntity<String> delete(@PathVariable String id) {
        boolean deleted = categoryService.deleteById(id);

        if (deleted) {
            return ResponseEntity.ok("Category deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }
    }
}
