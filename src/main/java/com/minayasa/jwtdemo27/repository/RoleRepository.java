package com.minayasa.jwtdemo27.repository;

import com.minayasa.jwtdemo27.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
