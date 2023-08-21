import { Component, OnDestroy } from '@angular/core';
import { map, switchMap } from 'rxjs';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnDestroy {

  proceedBtn = "Proceed to paiement";
  paimentPage = false;

  total$ = this._cart.total$;
  constructor(private _cart: CartService) { }
  /* cart$ = this._cart.cart$.pipe(
    switchMap(cart => {
      return this._cart.findCartById(cart.id).pipe(
        map(r => {
          return r.data
        })
      )
    })
  ) */
  cart$ = this._cart.cart$;

  ngOnDestroy(): void {
    this.updateCartInServer();
  }


  updateCartInServer() {
    this.cart$.pipe(
      switchMap(cart => {
        return this._cart.updateCartInDB(cart)
      })
    ).subscribe();
  }

  switchToPaiement() {
    this.paimentPage = !this.paimentPage
    this.proceedBtn = "Validate paiement";
    this.updateCartInServer();
  }

  cancelCheckout(event: any) {
    this.paimentPage = !this.paimentPage
    this.proceedBtn = "Proceed to paiement";
  }
}



