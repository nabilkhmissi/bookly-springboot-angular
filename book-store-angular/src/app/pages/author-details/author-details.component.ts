import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap, tap } from 'rxjs';
import { AuthorService } from 'src/app/services/author.service';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-author-details',
  templateUrl: './author-details.component.html',
  styleUrls: ['./author-details.component.css']
})
export class AuthorDetailsComponent {
  constructor(private _author: AuthorService,
    private _activatedRoute: ActivatedRoute,
    private _book: BookService) { }

  totalPages = 0;
  authorId: string | null = null;

  author$ = this._activatedRoute.paramMap.pipe(
    switchMap(paramMap => {
      return this._author.getById(paramMap.get("id")!);
    })
  );
  books$ = this._activatedRoute.paramMap.pipe(
    switchMap(paramMap => {
      this.authorId = paramMap.get("id")
      return this._book.findByAuthor(paramMap.get("id")!).pipe(
        tap(s => {
          this.totalPages = s.totalPages;
        })
      );
    })
  )

  changePage(event: any) {
    this.books$ = this._book.findByAuthor(this.authorId!, event);
  }
}
