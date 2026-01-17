package com.marcenaria.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orcamentos")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCliente;

    
    private LocalDateTime dataCriacao = LocalDateTime.now();

    
    private BigDecimal multiplicador = new BigDecimal("1.0");

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    
    @OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference 
    private List<OrcamentoItem> itens = new ArrayList<>();

    
    public Orcamento() {}

    
    public BigDecimal getTotalMateriais() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrcamentoItem item : itens) {
            total = total.add(item.getSubtotal());
        }
        return total;
    }

    public BigDecimal getValorFinal() {
        return getTotalMateriais().multiply(multiplicador);
    }

    public void adicionarItem(OrcamentoItem item) {
        item.setOrcamento(this); 
        this.itens.add(item);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public BigDecimal getMultiplicador() { return multiplicador; }
    public void setMultiplicador(BigDecimal multiplicador) { this.multiplicador = multiplicador; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<OrcamentoItem> getItens() { return itens; }
    public void setItens(List<OrcamentoItem> itens) { this.itens = itens; }
}