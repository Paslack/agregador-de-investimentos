package com.phc.agregador_de_investimentos.repository;

import com.phc.agregador_de_investimentos.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
