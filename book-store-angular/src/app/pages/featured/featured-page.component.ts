import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-featured-page',
  templateUrl: './featured-page.component.html',
  styleUrls: ['./featured-page.component.css']
})
export class FeaturedPageComponent implements OnInit {
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
    this._book.findFeatured(page).pipe(
      map(r => {
        this.totalPages = r.totalPages;
        return r.content;
      })
    ).subscribe(data => {
      this.books = data
    });
  }
}
