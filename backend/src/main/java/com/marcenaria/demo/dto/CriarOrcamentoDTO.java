package com.marcenaria.demo.dto;

import java.math.BigDecimal;
import java.util.List;

public record CriarOrcamentoDTO(
    String nomeCliente, 
    BigDecimal multiplicador, 
    List<ItemItemDTO> itens
    //JSON NO POSTMAN
) {
    
    public record ItemItemDTO(Long materialId, Integer quantidade) {}
}
