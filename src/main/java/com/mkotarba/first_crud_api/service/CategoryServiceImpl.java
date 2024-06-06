package com.mkotarba.first_crud_api.service;

import com.mkotarba.first_crud_api.collection.Category;
import com.mkotarba.first_crud_api.collection.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mkotarba.first_crud_api.respository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(String id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(String id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateById(String id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isEmpty()) {
            return false;
        } else {
            Category categoryToUpdate = optionalCategory.get();
            categoryToUpdate.setName(categoryDto.getName());
            categoryToUpdate.setDescription(categoryDto.getDescription());
            categoryRepository.save(categoryToUpdate);
            return true;
        }
    }
}
