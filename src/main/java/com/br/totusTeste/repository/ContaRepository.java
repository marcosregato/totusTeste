package com.br.totusTeste.repository;

import com.br.totusTeste.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContaRepository extends JpaRepository<Conta, Long> {
}
