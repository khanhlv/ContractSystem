package com.contract.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.contract.form.CategoryForm;
import com.contract.model.Category;
import com.contract.service.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findCategoryByStatus(Long status){
        return categoryRepository.findCategoryByStatus(status).stream().collect(Collectors.toList());
    }

    public List<Category> findAllWithPagination(CategoryForm categoryForm){
        Pageable pageable = PageRequest.of(
                categoryForm.getPage().intValue() == 0 ? 0 : categoryForm.getPage().intValue() - 1,
                categoryForm.getLimit().intValue());

        Page<Category> page = categoryRepository
                .findAllWithPagination(
                        "%" + StringUtils.defaultString(categoryForm.getCategoryName(), StringUtils.EMPTY) + "%",
                        categoryForm.getStatus() == null ? -1L : categoryForm.getStatus(),
                        pageable);
        categoryForm.setTotalRecord(page.getTotalElements());

        return page.stream().collect(Collectors.toList());
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }
}
