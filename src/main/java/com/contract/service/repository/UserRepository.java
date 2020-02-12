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
            value = "SELECT * FROM [USER] " +
                    "WHERE (:username = '' OR USERNAME LIKE :username) " +
                    "AND (:email = '' OR EMAIL = :email) " +
                    "AND (:phone = '' OR PHONE = :phone) " +
                    "AND (:userGroupId = '-1' OR USER_GROUP_ID = :userGroupId) " +
                    "AND (:companyId = '-1' OR COMPANY_ID = :companyId) " +
                    "AND (:status = '-1' OR STATUS = :status)",
            nativeQuery = true)
    Page<User> findAllWithPagination(@Param("username") String username,
                                     @Param("email") String email,
                                     @Param("phone") String phone,
                                     @Param("userGroupId") Long userGroupId,
                                     @Param("companyId") Long companyId,
                                     @Param("status") Long status,
                                     Pageable pageable);
}