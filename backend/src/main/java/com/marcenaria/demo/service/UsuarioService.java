package com.marcenaria.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.marcenaria.demo.model.Usuario;
import com.marcenaria.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

	public final UsuarioRepository repository;

	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	public Usuario salvar(Usuario usuario) {
		return repository.save(usuario);
	}
	
	public Optional<Usuario> buscarPorUsername(String username){
		return repository.findByUsername(username);
	}
}
