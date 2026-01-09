package com.marcenaria.demo.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.marcenaria.demo.model.Usuario;
import com.marcenaria.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;

	public final UsuarioRepository repository;

	public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public Usuario salvar(Usuario usuario) {
		//criptografia
		String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(senhaCriptografada);		
		
		return repository.save(usuario);
	}
	
	public Optional<Usuario> buscarPorUsername(String username){
		return repository.findByUsername(username);
	}
}
