import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, EMPTY, map, switchMap, tap } from 'rxjs';
import { ApiResponse } from '../models/apiResponse.model';
import { Cart } from '../models/cart.model';
import { CartItem } from '../models/cartItem.model';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private readonly baseUrl = `${environment.apiURL}/cart`;

  constructor(private _http: HttpClient, private _auth: AuthService) { }

  isOpen = new BehaviorSubject<boolean>(false);
  isOpen$ = this.isOpen.asObservable();

  total = new BehaviorSubject<number>(0);
  total$ = this.total.asObservable();

  cart = new BehaviorSubject<Cart>({ id: "null", items: [] } as Cart);

  cart$ = this.cart.asObservable().pipe(
    tap(cart => {
      let t = 0;
      if (cart.items.length !== 0) {
        cart.items.forEach(i => {
          t += (i.item.price * i.quantity)
        });
      }
      this.total.next(t);
    })
  );

  cartItems!: CartItem[];

  addToCart(item: CartItem, action: 'add' | 'remove' | 'update') {

    if (action === 'add') {
      if (this.cartItems.find(i => i.item.id === item.item.id)) {
        return;
      }
      this.cartItems.push(item);
    } else if (action === 'remove') {
      this.cartItems = this.cartItems.filter(i => i.item.id !== item.item.id);
    } else if (action == 'update') {

      const cartItemIndexToUpdate = this.cartItems.findIndex(i => i.item.id === item.item.id);
      const updatedItem = { ...this.cartItems[cartItemIndexToUpdate], quantity: item.quantity };
      const updatedCartItems = [
        ...this.cartItems.slice(0, cartItemIndexToUpdate),
        updatedItem,
        ...this.cartItems.slice(cartItemIndexToUpdate + 1),

      ]
      this.cartItems = updatedCartItems
    }

    let myCart: Cart = { id: this.cart.getValue().id, items: this.cartItems };
    localStorage.setItem("bookStoreCart", JSON.stringify(this.cartItems));
    this.cart.next(myCart)
    //after updating cart locally, send it to the server
    //this.updateCartInDB(myCart).subscribe();
  }

  updateCartInDB(cart: Cart) {
    return this._http.post(`${this.baseUrl}/update`, cart)
  }


  clearCart(id: string) {
    return this._http.delete<ApiResponse>(`${this.baseUrl}/${id}`).pipe(
      map(response => response.data)
    )
  }
  loadCartFromServer() {
    return this._auth.authenticatedUser$.pipe(
      switchMap(user => {
        if (!user) {
          return EMPTY
        }
        return this.findCartById(user.cartId).pipe(
          map(response => {
            this.cart.next(response.data);
            this.cartItems = response.data.items
            localStorage.setItem("bookStoreCart", JSON.stringify(response.data))
            return EMPTY;
          })
        )
      })
    )
  }

  findCartById(id: string) {
    return this._http.get<ApiResponse>(`${this.baseUrl}/${id}`)
  }

}
