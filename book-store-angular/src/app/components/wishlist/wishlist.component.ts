import { Component } from '@angular/core';
import { tap } from 'rxjs';
import { WishlistService } from 'src/app/services/wishlist.service';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent {

  constructor(private _wishlist: WishlistService) { }

  isOpen = false;
  number = 0;
  wishlist$ = this._wishlist.wishlist$.pipe(
    tap(list => {
      this.number = list.items.length
    })
  );

  ngOnInit(): void {
    this._wishlist.isOpen$.subscribe(
      d => { this.isOpen = d }
    )
  }
  hideCart() {
    this.isOpen = false;
  }
}
