package com.minayasa.jwtdemo27.repository;

import com.minayasa.jwtdemo27.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findByUsername(String userName);
}
