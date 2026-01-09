package com.marcenaria.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcenaria.demo.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {

}
