import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { LoginResponse } from '../../models/usuario.model';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private apiUrl = '/api/auth';
    private tokenKey = 'orcamento_token';

    private loggedIn = new BehaviorSubject<boolean>(this.hasToken());
    isLoggedIn$ = this.loggedIn.asObservable();

    constructor(private http: HttpClient) {
    }

    login(credentials: any): Observable<LoginResponse> {
        return this.http.post<LoginResponse>(`${this.apiUrl}/login`, credentials).pipe(
            tap(response => {
                this.salvarToken(response.token);
                this.loggedIn.next(true);
            })
        );
    }

    logout(): void {
        localStorage.removeItem(this.tokenKey);
        this.loggedIn.next(false);
    }

    getToken(): string | null {
        return localStorage.getItem(this.tokenKey);
    }

    private hasToken(): boolean {
        return !!localStorage.getItem(this.tokenKey);
    }

    private salvarToken(token: string): void {
        localStorage.setItem(this.tokenKey, token);
    }
}