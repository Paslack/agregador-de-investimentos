package com.phc.agregador_de_investimentos.repository;

import com.phc.agregador_de_investimentos.entities.AccountStock;
import com.phc.agregador_de_investimentos.entities.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
