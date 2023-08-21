import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { map, switchMap, tap } from 'rxjs';
import { AuthorService } from 'src/app/services/author.service';
import { BookService } from 'src/app/services/book.service';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-browse-filter',
  templateUrl: './browse-filter.component.html',
  styleUrls: ['./browse-filter.component.css']
})
export class BrowseFilterComponent implements OnInit {

  constructor(private _author: AuthorService,
    private _category: CategoryService,
    private _book: BookService) { }


  price_min = 0;
  price_max = 9999;
  year_min = 1900;
  year_max = new Date().getFullYear();
  pages_min = 10;
  pages_max = 99999;
  ngOnInit(): void {
    this.searchForm.valueChanges.pipe(
      tap(value => {
        this._book.author.next(value.author);
        this._book.category.next(value.category);
        /*        this.price_min = value.price_min ?? 0;
               this.price_max = value.price_max ?? 9999;
       
               this.year_min = value.year_min ?? this.year_min;
               this.year_max = value.year_max ?? this.year_max;
       
               this.pages_min = value.pages_min ?? this.pages_min;
               this.pages_max = value.pages_max ?? this.pages_max;
       
               this._book.pages_min.next(value.pages_min);
               this._book.pages_max.next(value.pages_max);
       
               this._book.price_min.next(value.price_min);
               this._book.price_max.next(value.price_max);
       
               this._book.year_min.next(value.year_min);
               this._book.year_max.next(value.price_max); */
      }),

    ).subscribe();
  }

  authors$ = this._author.getAll();
  categories$ = this._category.getAll();


  searchForm = new FormGroup({
    author: new FormControl(),
    category: new FormControl(),
    pages_min: new FormControl(),
    pages_max: new FormControl(),
    year_min: new FormControl(),
    year_max: new FormControl(),
    price_min: new FormControl(),
    price_max: new FormControl(),
    page: new FormControl(),
    size: new FormControl(),
  })

}
