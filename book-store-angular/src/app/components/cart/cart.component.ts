import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, tap } from 'rxjs';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(private _cart: CartService) { }

  isOpen = false;
  total$ = this._cart.total$;;
  cart$ = this._cart.cart$;

  ngOnInit(): void {
    this._cart.isOpen$.subscribe(
      d => { this.isOpen = d }
    )
  }

  hideCart() {
    this.isOpen = false;
  }
}
