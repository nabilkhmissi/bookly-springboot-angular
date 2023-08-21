import { Component } from '@angular/core';
import { map, tap } from 'rxjs';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-best-sellers-page',
  templateUrl: './best-sellers-page.component.html',
  styleUrls: ['./best-sellers-page.component.css']
})
export class BestSellersPageComponent {

  constructor(private _book: BookService) { }

  page = 0;
  totalPages = 0;
  books: Book[] = [];

  ngOnInit(): void {
    this.getAll(this.page);
  }

  changePage(event: any) {
    this.page = event
    this.getAll(event);
  }

  getAll(page: number) {
    this._book.findBestSellers(page).pipe(
      map(r => {
        this.totalPages = r.totalPages;
        return r.content;
      })
    ).subscribe(data => {
      this.books = data
    });
  }
}
