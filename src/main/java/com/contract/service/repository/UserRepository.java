package com.contract.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.contract.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT * FROM [USER] WHERE USERNAME = :username AND PASSWORD = :password AND COMPANY_ID = :companyId",
            nativeQuery = true)
    User checkLogin(@Param(value = "username") String username,
                    @Param(value = "password") String password,
                    @Param(value = "companyId") Long companyId);

    @Query(
            value = "SELECT * FROM [USER]",
            countQuery = "SELECT count(1) FROM [USER]",
            nativeQuery = true)
    Page<User> findAllWithPagination(Pageable pageable);
}