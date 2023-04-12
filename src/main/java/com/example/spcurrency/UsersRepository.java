package com.example.spcurrency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

//    Users findByUsername(String username);
    @Query("SELECT u FROM Users u where u.username = :username")
    Users findByLogin(@Param("username") String username);

    @Query("SELECT u FROM Users u WHERE u.username = :username")
    boolean existsByLogin(@Param("username") String username);
}
