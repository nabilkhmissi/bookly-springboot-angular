import { Component } from '@angular/core';
import { NotificationService } from './services/notification.service';
import { LoadingService } from './services/loading.service';
import { AuthService } from './services/auth.service';
import { CartService } from './services/cart.service';
import { WishlistService } from './services/wishlist.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private _notification: NotificationService,
    private _loading: LoadingService,
    private _auth: AuthService,
    private _cart: CartService,
    private _wishlist: WishlistService) {
    this._auth.autoLogin();
    this._cart.loadCartFromServer().subscribe()
    this._wishlist.loadWishlistFromServer().subscribe()
  }
  notification$ = this._notification.notifcation$;
  loading$ = this._loading.loading$;
  title = 'book-store-angular';
}
