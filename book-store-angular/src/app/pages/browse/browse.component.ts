import { Component } from '@angular/core';
import { map, tap } from 'rxjs';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-browse',
  templateUrl: './browse.component.html',
  styleUrls: ['./browse.component.css']
})
export class BrowseComponent {

  totalPages = 0;
  emptySearch = false;
  page = 0;

  constructor(private _books: BookService) { }
  books$ = this._books.books$.pipe(
    map(response => {
      this.totalPages = response.totalPages;
      this.emptySearch = response.content.length > 0 ? false : true
      return response.content
    })
  );

  searchBook(item : string) {
    this._books.title.next(item);
  }

  changePage(page: any) {
    this.page = page;
    this._books.page.next(page)
  }
}
