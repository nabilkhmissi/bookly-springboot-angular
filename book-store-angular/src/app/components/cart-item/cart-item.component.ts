import { Component, Input } from '@angular/core';
import { CartItem } from 'src/app/models/cartItem.model';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent {

  constructor(private _cart: CartService) { }
  @Input() item!: CartItem;
  quantity = 0;


  removeItem() {
    this._cart.addToCart(this.item, 'remove');
  }

}
