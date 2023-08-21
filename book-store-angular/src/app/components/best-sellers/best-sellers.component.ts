import { Component } from '@angular/core';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-best-sellers',
  templateUrl: './best-sellers.component.html',
  styleUrls: ['./best-sellers.component.css']
})
export class BestSellersComponent {
  constructor(private _book: BookService) { }

  bestSellers$ = this._book.findBestSellers(0, 5);
}
