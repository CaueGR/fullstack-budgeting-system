import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.services';
import { MaterialService } from '../../core/services/material.service';
import { Material } from '../../models/material.model';

@Component({
    selector: 'app-dashboard',
    standalone: true,
    imports: [CommonModule, ReactiveFormsModule],
    templateUrl: './dashboard.html',
    styleUrl: './dashboard.scss'
})
export class DashboardComponent {
    materiais: Material[] = [];
    materialForm: FormGroup;
    exibirFormulario = false;


    constructor(
        private authService: AuthService,
        private router: Router,
        private materialService: MaterialService,
        private fb: FormBuilder
    ) {
        this.carregarMateriais();
        this.materialForm = this.fb.group({
            name: ['', Validators.required],
            precoUnitario: ['', [Validators.required, Validators.min(0.01)]]
        });
    }


    carregarMateriais(): void {
        this.materialService.listar().subscribe({
            next: (dados) => {
                this.materiais = dados;
                console.log('Materiais carregados:', dados);
            },
            error: (erro) => {
                console.error('Erro ao buscar materiais:', erro);
            }
        });
    }
    toggleFormulario(): void {
        this.exibirFormulario = !this.exibirFormulario;
    }

    salvarMaterial(): void {
        if (this.materialForm.invalid) return;

        const novoMaterial: Material = this.materialForm.value;

        this.materialService.criar(novoMaterial).subscribe({
            next: (materialCriado) => {

                console.log('Criado:', materialCriado);

                this.materiais.push(materialCriado);

                this.materialForm.reset();
                this.exibirFormulario = false;
            },
            error: (err) => console.error('Erro ao salvar:', err)
        });
    }
    logout(): void {
        this.authService.logout();
        this.router.navigate(['/login']);
    }
}

