package com.marcenaria.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcenaria.demo.model.Usuario;
import com.marcenaria.demo.service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final UsuarioService service;

	public AuthController(UsuarioService service) {
		this.service = service;
	}
	
	@PostMapping("/register")
	public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
	    Usuario novoUsuario = service.salvar(usuario);
	    return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
	}
	
}
