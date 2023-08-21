import { Component, Input } from '@angular/core';
import { Book } from 'src/app/models/book.model';
import { WishlistService } from 'src/app/services/wishlist.service';

@Component({
  selector: 'app-wishlist-item',
  templateUrl: './wishlist-item.component.html',
  styleUrls: ['./wishlist-item.component.css']
})
export class WishlistItemComponent {
  constructor(private _wishlist: WishlistService) { }
  @Input() item!: Book;


  removeItem() {
    this._wishlist.addToWishlist(this.item, 'remove');
  }
}
