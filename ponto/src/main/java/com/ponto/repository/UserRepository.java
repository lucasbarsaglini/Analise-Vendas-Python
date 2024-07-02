package com.ponto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ponto.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

