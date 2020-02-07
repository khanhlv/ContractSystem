package com.contract.service.repository;


import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.contract.model.Category;


public interface CategoryRepository extends CrudRepository<Category, Long> {
    Collection<Category> findCategoryByStatus(Long status);
}