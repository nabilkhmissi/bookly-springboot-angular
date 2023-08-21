import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoadingService } from './loading.service';
import { AuthResponse } from '../models/authResponse.model';
import { AuthRequest } from '../models/authRequest.model';
import { BehaviorSubject, tap } from 'rxjs';
import { ApiResponse } from '../models/apiResponse.model';
import { User } from '../models/user.model';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { RegisterRequest } from '../models/registerRequest.model';
import { NotificationService } from './notification.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly baseUrl = `${environment.apiURL}/auth`;

  isAuthenticated = new BehaviorSubject<boolean>(false);
  isAuthenticated$ = this.isAuthenticated.asObservable();

  authenticatedUser = new BehaviorSubject<User | null>(null);
  authenticatedUser$ = this.authenticatedUser.asObservable();

  constructor(private _http: HttpClient,
    private _loading: LoadingService,
    private _router: Router,
    private _notification: NotificationService) { }

  login(auth: AuthRequest) {
    this._loading.showLoading();
    return this._http.post<ApiResponse>(`${this.baseUrl}/authenticate`, auth).pipe(
      tap(response => {
        this.saveAuthToLS(response.data)
        this.isAuthenticated.next(true);
        this.authenticatedUser.next(response.data.user);
        this._loading.hideLoading();
        this._router.navigate(['/'])
      })
    )
  }

  register(auth: RegisterRequest) {
    this._loading.showLoading();
    return this._http.post<ApiResponse>(`${this.baseUrl}/register`, auth).pipe(
      tap(response => {
        /*      this.saveAuthToLS(response.data)
             this.isAuthenticated.next(true);
             this.authenticatedUser.next(response.data.user);*/
        this._notification.showNotification(response.data);
        this._loading.hideLoading();
        this._router.navigate(['/login'])
      })
    )
  }

  saveAuthToLS(data: AuthResponse) {
    localStorage.setItem("bookStore", JSON.stringify(data))
  }

  loadAuthFromLS(): AuthResponse | null {
    let auth = localStorage.getItem("bookStore");
    if (auth) {
      return JSON.parse(auth);
    }
    return null;
  }

  autoLogin() {
    let auth = this.loadAuthFromLS();
    if (auth != null) {
      this.isAuthenticated.next(true);
      this.authenticatedUser.next(auth.user);
    }
  }

  logout() {
    this.authenticatedUser.next(null);
    this.isAuthenticated.next(false)
    localStorage.removeItem("bookStore")
    localStorage.removeItem("bookStoreCart")
    localStorage.removeItem("bookStoreWishlist")
  }
}
