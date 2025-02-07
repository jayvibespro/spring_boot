package com.jaycode.demo.features.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findUserByUserName(String userName);
}
