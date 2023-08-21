import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, map } from 'rxjs';
import { ApiResponse } from '../models/apiResponse.model';
import { LoadingService } from './loading.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  private readonly baseUrl = `${environment.apiURL}/author`;

  constructor(private _http: HttpClient, private _loading: LoadingService) { }

  private title = new BehaviorSubject<string | null>(null);
  title$ = this.title.asObservable();

  private pages_min = new BehaviorSubject<number | null>(null);
  pages_min$ = this.pages_min.asObservable();


  searchByCriteres(page?: number, size?: number) {
    this._loading.showLoading();
    let request = this.buildRequest(page, size);
    return this._http.get<ApiResponse>(`${this.baseUrl}/search?${request}`).pipe(
      map(response => {
        this._loading.hideLoading();
        return response.data
      })
    )
  }

  getAllAuthorsByPage(page?: number, size?: number) {
    this._loading.showLoading();
    let request = this.buildRequest(page, size);
    return this._http.get<ApiResponse>(`${this.baseUrl}?${request}`).pipe(
      map(response => {
        this._loading.hideLoading();
        return response.data
      })
    )
  }

  getAll() {
    this._loading.showLoading();
    return this._http.get<ApiResponse>(`${this.baseUrl}/all`).pipe(
      map(response => {
        this._loading.hideLoading();
        return response.data
      })
    )
  }

  getById(id: string) {
    return this._http.get<ApiResponse>(`${this.baseUrl}/${id}`).pipe(
      map(response => response.data)
    )
  }

  buildRequest(page?: number, size?: number) {
    let request = "";
    if (page != null) { request += `page=${page}&`; }
    if (size != null) { request += `size=${size}&`; }
    return request;
  }
}
