import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Material } from '../../models/material.model';

@Injectable({
    providedIn: 'root'
})
export class MaterialService {
    private apiUrl = '/api/materiais';

    constructor(private http: HttpClient) { }

    listar(): Observable<Material[]> {

        return this.http.get<Material[]>(this.apiUrl);
    }

    criar(material: Material): Observable<Material> {
        return this.http.post<Material>(this.apiUrl, material);
    }
}