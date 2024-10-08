package com.ofss.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ofss.main.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
