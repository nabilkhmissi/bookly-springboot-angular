import { Component } from '@angular/core';
import { OwlOptions } from 'ngx-owl-carousel-o';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent {
  constructor(private _book: BookService) { }

  featured$ = this._book.getFeatured(0, 7);
  newest$ = this._book.findNewest(0, 4);
  customOptions: OwlOptions = {
    loop: true,
    mouseDrag: true,
    touchDrag: true,
    pullDrag: true,
    dots: true,
    navSpeed: 700,
    navText: ['', ''],
    responsive: {
      0: {
        items: 1
      },
      400: {
        items: 2
      },
      740: {
        items: 3
      },
      940: {
        items: 4
      }
    },
    nav: false,
    autoplay: true,
    autoplayTimeout: 2000,
   /*  margin: 20 */
  }
}
