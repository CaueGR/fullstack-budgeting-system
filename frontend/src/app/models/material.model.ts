import { Usuario } from "./usuario.model";

export interface Material {
    id?: number;
    name: string;
    precoUnitario: number;
    usuario?: Usuario;
}