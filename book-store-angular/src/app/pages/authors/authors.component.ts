import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { AuthorService } from 'src/app/services/author.service';

@Component({
  selector: 'app-authors',
  templateUrl: './authors.component.html',
  styleUrls: ['./authors.component.css']
})
export class AuthorsComponent implements OnInit {

  constructor(private _author: AuthorService) { }

  totalPages = 0;
  page = 0;
  emptySearch = false;
  authors = [];

  ngOnInit(): void {
    this.getAll(this.page);
  }


  changePage(page: any) {
    this.page = page
    this.getAll(page);
  }


  getAll(page: number) {
    this._author.getAllAuthorsByPage(this.page, undefined).pipe(
      map(r => {
        this.totalPages = r.totalPages;
        this.emptySearch = r.content.length == 0 ? true : false;
        return r.content;
      })
    ).subscribe(data => { this.authors = data });
  }

}
