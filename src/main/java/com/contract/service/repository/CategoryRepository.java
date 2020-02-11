package com.contract.service.repository;


import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.contract.model.Category;


public interface CategoryRepository extends CrudRepository<Category, Long> {
    Collection<Category> findCategoryByStatus(Long status);

    @Query(
            value = "SELECT * FROM [CATEGORY] " +
                    "WHERE (:categoryName = '' OR CATEGORY_NAME LIKE :categoryName) " +
                    "AND (:status = '-1' OR STATUS = :status)",
            countQuery = "SELECT count(1) FROM [CATEGORY] " +
                    "WHERE (:categoryName = '' OR CATEGORY_NAME LIKE :categoryName) " +
                    "AND (:status = '-1' OR STATUS = :status)",
            nativeQuery = true)
    Page<Category> findAllWithPagination(@Param("categoryName") String categoryName,
                                        @Param("status") Long status, Pageable pageable);
}