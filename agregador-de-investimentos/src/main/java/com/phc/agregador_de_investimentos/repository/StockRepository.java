package com.phc.agregador_de_investimentos.repository;

import com.phc.agregador_de_investimentos.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
}
