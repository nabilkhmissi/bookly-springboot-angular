import { Component } from '@angular/core';
import { AuthorService } from 'src/app/services/author.service';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent {
  constructor(private _book: BookService, private _author: AuthorService) { }

  featured$ = this._book.getFeatured(0, 7);
  newest$ = this._book.findNewest(0, 4);
}
