import { Component, Input } from '@angular/core';
import { CartItem } from 'src/app/models/cartItem.model';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-checkout-item',
  templateUrl: './checkout-item.component.html',
  styleUrls: ['./checkout-item.component.css']
})
export class CheckoutItemComponent {
  constructor(private _cart: CartService) { }
  @Input() item!: CartItem;
  quantity = 0;

  removeItem() {
    this._cart.addToCart(this.item, 'remove');
  }

  updateQuantity(event: any) {
    let new_item: CartItem = { item: this.item.item, quantity: event.target.value };
    this.item = new_item;
    this._cart.addToCart(new_item, 'update');
  }
}
