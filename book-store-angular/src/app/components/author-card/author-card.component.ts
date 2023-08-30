import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Author } from 'src/app/models/author.model';

@Component({
  selector: 'app-author-card',
  templateUrl: './author-card.component.html',
  styleUrls: ['./author-card.component.css']
})
export class AuthorCardComponent {

  @Input() author!: Author;
  constructor(private _router: Router) { }

  goToAuthor() {
    this._router.navigateByUrl("/authors/details/" + this.author.id)
  }
}
