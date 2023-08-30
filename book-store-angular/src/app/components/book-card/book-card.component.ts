import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from 'src/app/models/book.model';
import { AuthService } from 'src/app/services/auth.service';
import { CartService } from 'src/app/services/cart.service';
import { NotificationService } from 'src/app/services/notification.service';
import { WishlistService } from 'src/app/services/wishlist.service';

@Component({
  selector: 'app-book-card',
  templateUrl: './book-card.component.html',
  styleUrls: ['./book-card.component.css']
})
export class BookCardComponent {

  constructor(
    private _router: Router,
    private _cart: CartService,
    private _wishlist: WishlistService,
    private _auth: AuthService,
    private _notification: NotificationService
  ) { }
  @Input() book!: Book;


  goToDetails(id: string) {
    this._router.navigate(['books', 'details', id])
  }

  addToCart() {
    const user = this._auth.authenticatedUser.getValue();
    if (user) {
      this._cart.addToCart({ item: this.book, quantity: 1 }, 'add');
      return;
    }
    this._notification.showNotification("You have to sign in first to add this book to cart")
  }

  addToWishlist() {
    const user = this._auth.authenticatedUser.getValue();
    if (user) {
      this._wishlist.addToWishlist(this.book, 'add');
      return;
    }
    this._notification.showNotification("You have to sign in first to add this book to wishlist")
  }
}
