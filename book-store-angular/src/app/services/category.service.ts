import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { ApiResponse } from '../models/apiResponse.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private readonly baseUrl = `${environment.apiURL}/category`;

  constructor(private _http: HttpClient) { }

  getAll() {
    return this._http.get<ApiResponse>(`${this.baseUrl}`).pipe(
      map(response => response.data)
    )
  }

}
