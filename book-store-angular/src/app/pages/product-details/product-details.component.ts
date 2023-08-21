import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap, tap } from 'rxjs';
import { Book } from 'src/app/models/book.model';
import { CartItem } from 'src/app/models/cartItem.model';
import { AuthService } from 'src/app/services/auth.service';
import { BookService } from 'src/app/services/book.service';
import { CartService } from 'src/app/services/cart.service';
import { NotificationService } from 'src/app/services/notification.service';
import { WishlistService } from 'src/app/services/wishlist.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  constructor(private _books: BookService,
    private _activatedRoute: ActivatedRoute,
    private _cart: CartService,
    private _wishlist: WishlistService,
    private _auth: AuthService,
    private _notification: NotificationService) { }


  inCart = false;
  inWishlist = false;

  book$ = this._activatedRoute.paramMap.pipe(
    switchMap(paramMap => {
      return this._books.findById(paramMap.get("id")!)
    })
  )
  ngOnInit(): void {
  }


  addToCart(book: Book) {
    const user = this._auth.authenticatedUser.getValue();
    if (!user) {
      this._notification.showNotification("You have to sign in first to add this book to cart")
      return;
    }
    this.inCart = !this.inCart
    let item: CartItem = { item: book, quantity: 1 }
    this._cart.addToCart(item, 'add')
  }

  addToWishlist(book: Book) {
    const user = this._auth.authenticatedUser.getValue();
    if (!user) {
      this._notification.showNotification("You have to sign in first to add this book to wishlist")
      return;
    }
    this.inWishlist = !this.inWishlist
    this._wishlist.addToWishlist(book, 'add');
  }

}
