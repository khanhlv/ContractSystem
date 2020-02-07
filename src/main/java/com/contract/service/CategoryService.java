package com.contract.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contract.model.Category;
import com.contract.service.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findCategoryByStatus(Long status){
        return categoryRepository.findCategoryByStatus(status).stream().collect(Collectors.toList());
    }
}
