package com.phc.agregador_de_investimentos.repository;

import com.phc.agregador_de_investimentos.entities.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long> {
}
