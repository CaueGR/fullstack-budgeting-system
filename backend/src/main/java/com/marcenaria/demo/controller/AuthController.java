package com.marcenaria.demo.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.marcenaria.demo.dto.LoginDTO;
import com.marcenaria.demo.infra.security.TokenService;
import com.marcenaria.demo.model.Usuario;
import com.marcenaria.demo.service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final UsuarioService service;
	private final PasswordEncoder passwordEncoder;
	private final TokenService tokenService;

	public AuthController(UsuarioService service, PasswordEncoder passwordEncoder, TokenService tokenService) {
		this.service = service;
		this.passwordEncoder = passwordEncoder;
		this.tokenService = tokenService;
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginDTO data) {
		Optional<Usuario> userOptional = service.buscarPorUsername(data.username());

		if (userOptional.isEmpty()) {
			return ResponseEntity.badRequest().body("Usuário não encontrado");
		}

		Usuario usuario = userOptional.get();
		if (!passwordEncoder.matches(data.password(), usuario.getPassword())) {
			return ResponseEntity.badRequest().body("Senha inválida");
		}

		String token = tokenService.gerarToken(usuario);
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}

	@PostMapping("/register")
	public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
		Usuario novoUsuario = service.salvar(usuario);
		return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
	}

	public record LoginResponseDTO(String token) {
	}
}
