import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, EMPTY, catchError, combineLatest, map, switchMap, tap } from 'rxjs';
import { LoadingService } from './loading.service';
import { ApiResponse } from '../models/apiResponse.model';
import { AuthorService } from './author.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private readonly baseUrl = `${environment.apiURL}/book`;

  constructor(private _http: HttpClient,
    private _loading: LoadingService,
    private _author: AuthorService) { }

  title = new BehaviorSubject<string | null>(null);
  title$ = this.title.asObservable();

  category = new BehaviorSubject<string | null>(null);
  category$ = this.category.asObservable();

  author = new BehaviorSubject<string | null>(null);
  author$ = this.author.asObservable();

  pages_min = new BehaviorSubject<number | null>(null);
  pages_min$ = this.pages_min.asObservable();

  pages_max = new BehaviorSubject<number | null>(null);
  pages_max$ = this.pages_max.asObservable();

  year_min = new BehaviorSubject<number | null>(null);
  year_min$ = this.year_min.asObservable();

  year_max = new BehaviorSubject<number | null>(null);
  year_max$ = this.year_max.asObservable();

  price_min = new BehaviorSubject<number | null>(null);
  price_min$ = this.price_min.asObservable();

  price_max = new BehaviorSubject<number | null>(null);
  price_max$ = this.price_max.asObservable();

  page = new BehaviorSubject<number | null>(null);
  page$ = this.page.asObservable();

  size = new BehaviorSubject<number | null>(null);
  size$ = this.size.asObservable();


  books$ = combineLatest([this.author$,
  this.category$,
  this.title$,
  this.pages_min$,
  this.pages_max$,
  this.year_min$,
  this.year_max$,
  this.price_min$,
  this.price_max$,
  this.page$,
  this.size$,
  ]).pipe(
    switchMap(([author, category, title, pages_min, pages_max, year_min, year_max, price_min, price_max, page, size]) => {
      this._loading.showLoading();
      const request = this.buildRequest(author, category, title, pages_min, pages_max, year_min, year_max, price_min, price_max, page, size);
      return this._http.get<any>(`${this.baseUrl}/search?${request}`).pipe(
        map(response => {
          this._loading.hideLoading();
          return response.data
        }),
        catchError(error => {
          this._loading.hideLoading();
          return EMPTY;
        })
      )
    })
  )

  buildRequest(author: any, category: any, title: any, pages_min: any, pages_max: any, year_min: any, year_max: any, price_min: any, price_max: any, page: any, size: any) {
    let request = "";
    if (author != null) { request += `author=${author}&` }
    if (category != null) { request += `category=${category}&` }
    if (title != null) { request += `title=${title}&` }
    if (pages_min != null) { request += `title=${pages_min}&` }
    if (pages_max != null) { request += `title=${pages_max}&` }
    if (year_min != null) { request += `title=${year_min}&` }
    if (year_max != null) { request += `title=${year_max}&` }
    if (price_min != null) { request += `title=${price_min}&` }
    if (price_max != null) { request += `title=${price_max}&` }
    if (page != null) { request += `page=${page}&` }
    if (size != null) { request += `size=${size}&` }
    return request;
  }

  getFeatured(page: number, size: number) {
    this._loading.showLoading();
    let request = "";
    if (page) { request += `page=${page}&` };
    if (size) { request += `size=${size}&` };
    return this._http.get<ApiResponse>(`${this.baseUrl}/featured?${request}`).pipe(
      map(response => {
        this._loading.hideLoading();
        return response.data
      })
    );
  }
  findBestSellers(page?: number, size?: number) {
    this._loading.showLoading();
    let request = "";
    if (page) { request += `page=${page}&` };
    if (size) { request += `size=${size}&` };
    return this._http.get<ApiResponse>(`${this.baseUrl}/best-sellers?${request}`).pipe(
      map(response => {
        this._loading.hideLoading();
        return response.data
      })
    );
  }

  findFeatured(page?: number, size?: number) {
    this._loading.showLoading();
    let request = "";
    if (page) { request += `page=${page}&` };
    if (size) { request += `size=${size}&` };
    return this._http.get<ApiResponse>(`${this.baseUrl}/featured?${request}`).pipe(
      map(response => {
        this._loading.hideLoading();
        return response.data
      })
    );
  }

  findById(id: string) {
    this._loading.showLoading();
    return this._http.get<ApiResponse>(`${this.baseUrl}/${id}`).pipe(
      switchMap(response => {
        return this._author.getById(response.data.authorId).pipe(
          map(author => {
            this._loading.hideLoading();
            return {
              ...response.data,
              author: author
            }
          })
        )
      })
    );
  }

  findByAuthor(id: string, page?: number, size?: number) {
    this._loading.showLoading();
    let request = "";
    if (page) { request += `page=${page}&` };
    if (size) { request += `size=${size}&` };
    return this._http.get<ApiResponse>(`${this.baseUrl}/author/${id}?${request}`).pipe(
      map(response => {
        this._loading.hideLoading();
        return response.data
      })
    );
  }

  findNewest(page?: number, size?: number) {
    this._loading.showLoading();
    let request = "";
    if (page) { request += `page=${page}&` };
    if (size) { request += `size=${size}&` };
    return this._http.get<ApiResponse>(`${this.baseUrl}/newest?${request}`).pipe(
      map(response => {
        this._loading.hideLoading();
        return response.data
      })
    );
  }

}
