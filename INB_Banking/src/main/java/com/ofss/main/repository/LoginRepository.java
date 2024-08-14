package com.ofss.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ofss.main.domain.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {
}