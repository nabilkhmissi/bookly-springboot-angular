import { Component } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { CartService } from 'src/app/services/cart.service';
import { WishlistService } from 'src/app/services/wishlist.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  constructor(private _auth: AuthService, 
    private _cart: CartService, 
    private _wishlist: WishlistService) { }

  isAuthenticated$ = this._auth.isAuthenticated$;
  isOpened = false;
  wishlistIsOpened = false;
  cart$ = this._cart.cart$;

  toggleCart(event: Event) {
    event.preventDefault();
    this._cart.isOpen.next(!this.isOpened)
    this.isOpened = !this.isOpened
  }

  logout(event: Event) {
    event.preventDefault();
    this._auth.logout()
  }

  toggleWishlist(event: Event) {
    event.preventDefault();
    this._wishlist.isOpen.next(!this.wishlistIsOpened)
    this.wishlistIsOpened = !this.wishlistIsOpened
  }
}
