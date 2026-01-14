package com.marcenaria.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcenaria.demo.model.Material;
import com.marcenaria.demo.model.Usuario;
import com.marcenaria.demo.service.MaterialService;

@RestController
@RequestMapping("/api/materiais")
public class MaterialController {

	public final MaterialService service;

	public MaterialController(MaterialService service) {
		this.service = service;
	}

	@GetMapping
	public List<Material> listar() {
		return service.listarTudo();
	}

	@PostMapping
	public ResponseEntity<Material> criar(@RequestBody Material novo) {
		Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		novo.setUsuario(usuarioLogado);

		Material novoMaterial = service.salvar(novo);
		return ResponseEntity.ok(novoMaterial);
	}

}
