package com.marcenaria.demo.controller;

import com.marcenaria.demo.dto.CriarOrcamentoDTO;
import com.marcenaria.demo.model.Orcamento;
import com.marcenaria.demo.model.Usuario;
import com.marcenaria.demo.service.OrcamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orcamentos")
public class OrcamentoController {

    private final OrcamentoService service;

    public OrcamentoController(OrcamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Orcamento> criar(@RequestBody CriarOrcamentoDTO dados) {
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Orcamento novoOrcamento = service.criarOrcamento(dados, usuarioLogado);
        return ResponseEntity.ok(novoOrcamento);
    }

    @GetMapping
    public List<Orcamento> listar() {
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return service.listarTodos(usuarioLogado);
    }
}