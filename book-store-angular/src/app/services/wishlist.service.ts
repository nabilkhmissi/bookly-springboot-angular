import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, EMPTY, map, switchMap } from 'rxjs';
import { ApiResponse } from '../models/apiResponse.model';
import { AuthService } from './auth.service';
import { Book } from '../models/book.model';
import { Wishlist } from '../models/wishlist.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {

  private readonly baseUrl = `${environment.apiURL}/wishlist`;

  constructor(private _http: HttpClient, private _auth: AuthService) { }

  isOpen = new BehaviorSubject<boolean>(false);
  isOpen$ = this.isOpen.asObservable();

  wishlist = new BehaviorSubject<Wishlist>({ id: "null", items: [] } as Wishlist);

  wishlist$ = this.wishlist.asObservable();

  wishlistItems!: Book[];

  addToWishlist(item: Book, action: 'add' | 'remove') {

    if (action === 'add') {
      if (this.wishlistItems.find(i => i.id === item.id)) {
        return;
      }
      this.wishlistItems.push(item);
    } else {
      this.wishlistItems = this.wishlistItems.filter(i => i.id !== item.id);
    }

    let myWishlist: Wishlist = { id: this.wishlist.getValue().id, items: this.wishlistItems };
    this.wishlist.next(myWishlist)

    localStorage.setItem("bookStoreWishlist", JSON.stringify(this.wishlistItems));
    //after updating wishlist locally, send it to the server
    this.updateCartInDB(myWishlist).subscribe();
  }

  updateCartInDB(wishlist: Wishlist) {
    return this._http.post(`${this.baseUrl}/update`, wishlist)
  }


  loadWishlistFromServer() {
    return this._auth.authenticatedUser$.pipe(
      switchMap(user => {
        if (!user) {
          return EMPTY
        }
        return this._http.get<ApiResponse>(`${this.baseUrl}/${user?.wishlistId}`).pipe(
          map(response => {
            this.wishlist.next(response.data);
            this.wishlistItems = response.data.items
            localStorage.setItem("bookStoreWishlist", JSON.stringify(response.data))
            return EMPTY;
          })
        )
      })
    )
  }

}
