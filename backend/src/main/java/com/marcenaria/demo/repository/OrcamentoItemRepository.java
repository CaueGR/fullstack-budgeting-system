package com.marcenaria.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcenaria.demo.model.OrcamentoItem;

public interface OrcamentoItemRepository extends JpaRepository<OrcamentoItem, Long> {

}
