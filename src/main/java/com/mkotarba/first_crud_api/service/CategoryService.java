package com.mkotarba.first_crud_api.service;

import com.mkotarba.first_crud_api.collection.Category;
import com.mkotarba.first_crud_api.collection.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Category save(Category category);

    List<Category> findAll();

    Category findById(String id);

    boolean deleteById(String category);

    boolean updateById(String id, CategoryDto categoryDto);
}
