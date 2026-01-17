package com.marcenaria.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.marcenaria.demo.model.Orcamento;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
}
