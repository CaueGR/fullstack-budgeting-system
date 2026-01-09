package com.marcenaria.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.marcenaria.demo.model.Material;
import com.marcenaria.demo.repository.MaterialRepository;

@Service
public class MaterialService {

	private final MaterialRepository repository;

	public MaterialService(MaterialRepository repository) {
		this.repository = repository;
	}
	
	public List<Material> listarTudo(){
		return repository.findAll();
	}
	
	public Material salvar(Material novoMaterial){
		return repository.save(novoMaterial);
	}
	
	
	
}
