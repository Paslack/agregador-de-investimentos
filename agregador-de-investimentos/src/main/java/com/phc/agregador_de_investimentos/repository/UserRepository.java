package com.phc.agregador_de_investimentos.repository;

import com.phc.agregador_de_investimentos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
