package com.marcenaria.demo.service;

import com.marcenaria.demo.dto.CriarOrcamentoDTO;
import com.marcenaria.demo.model.Material;
import com.marcenaria.demo.model.Orcamento;
import com.marcenaria.demo.model.OrcamentoItem;
import com.marcenaria.demo.model.Usuario;
import com.marcenaria.demo.repository.MaterialRepository;
import com.marcenaria.demo.repository.OrcamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrcamentoService {

    private final OrcamentoRepository orcamentoRepository;
    private final MaterialRepository materialRepository;

    public OrcamentoService(OrcamentoRepository orcamentoRepository, MaterialRepository materialRepository) {
        this.orcamentoRepository = orcamentoRepository;
        this.materialRepository = materialRepository;
    }

    @Transactional
    public Orcamento criarOrcamento(CriarOrcamentoDTO dados, Usuario dono) {
        Orcamento orcamento = new Orcamento();
        orcamento.setNomeCliente(dados.nomeCliente());
        orcamento.setMultiplicador(dados.multiplicador());
        orcamento.setUsuario(dono);

        
        for (CriarOrcamentoDTO.ItemItemDTO itemDTO : dados.itens()) {
            Material material = materialRepository.findById(itemDTO.materialId())
                    .orElseThrow(() -> new RuntimeException("Material não encontrado: " + itemDTO.materialId()));

            OrcamentoItem item = new OrcamentoItem();
            item.setMaterial(material);
            item.setQuantidade(itemDTO.quantidade());
            
            item.setPrecoNoMomento(material.getPrecoUnitario()); 
            orcamento.adicionarItem(item);
        }
        return orcamentoRepository.save(orcamento);
    }
    
    public List<Orcamento> listarTodos(Usuario dono) {
        return orcamentoRepository.findAll();
    }
}
