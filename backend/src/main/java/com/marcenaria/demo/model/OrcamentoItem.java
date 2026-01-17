package com.marcenaria.demo.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orcamento_itens")
public class OrcamentoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal precoNoMomento;

    @ManyToOne
    @JoinColumn(name = "orcamento_id")
    @JsonBackReference
    private Orcamento orcamento;

    public OrcamentoItem() {
    }

    public OrcamentoItem(Material material, Integer quantidade, BigDecimal precoNoMomento) {
        this.material = material;
        this.quantidade = quantidade;
        this.precoNoMomento = precoNoMomento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoNoMomento() {
        return precoNoMomento;
    }

    public void setPrecoNoMomento(BigDecimal precoNoMomento) {
        this.precoNoMomento = precoNoMomento;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public BigDecimal getSubtotal() {
        if (precoNoMomento == null || quantidade == null)
            return BigDecimal.ZERO;
        return precoNoMomento.multiply(new BigDecimal(quantidade));
    }
}
