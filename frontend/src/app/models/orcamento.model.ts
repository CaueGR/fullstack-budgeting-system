import { Material } from "./material.model";

export interface OrcamentoItem {
    id?: number;
    material: Material;
    quantidade: number;
    precoNoMomento?: number;
    subtotal?: number;
}

export interface Orcamento {
    id?: number;
    nomeCliente: string;
    multiplicador: number;
    itens: OrcamentoItem[];
    valorFinal?: number;
    totalMateriais?: number;
    dataCriacao?: string;
}


export interface CriarOrcamentoDTO {
    nomeCliente: string;
    multiplicador: number;
    itens: {
        materialId: number;
        quantidade: number;
    }[];
}