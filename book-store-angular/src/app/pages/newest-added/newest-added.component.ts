import { Component, OnInit } from '@angular/core';
import { map, tap } from 'rxjs';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-newest-added',
  templateUrl: './newest-added.component.html',
  styleUrls: ['./newest-added.component.css']
})
export class NewestAddedComponent implements OnInit {

  constructor(private _book: BookService) { }
  page = 0;
  totalPages = 0;
  books: Book[] = [];

  ngOnInit(): void {
    this.getAll(this.page);
  }

 /*  books$ = this._book.findNewest(this.page).pipe(
    tap(r => {
      this.totalPages = r.totalPages
    })
  ) */

  changePage(event: any) {
    this.page = event
    this.getAll(event);
  }

  getAll(page: number) {
    this._book.findNewest(page).pipe(
      map(r => {
        this.totalPages = r.totalPages;
        return r.content;
      })
    ).subscribe(data => {
      this.books = data
    });
  }
}
