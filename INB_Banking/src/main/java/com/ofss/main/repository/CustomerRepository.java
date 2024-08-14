package com.ofss.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ofss.main.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
