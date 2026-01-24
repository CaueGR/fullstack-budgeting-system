import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.services';

@Component({
    selector: 'app-dashboard',
    standalone: true,
    imports: [],
    templateUrl: './dashboard.html',
    styleUrl: './dashboard.scss'
})
export class DashboardComponent {

    constructor(private authService: AuthService, private router: Router) { }

    logout(): void {
        this.authService.logout();
        this.router.navigate(['/login']);
    }
}