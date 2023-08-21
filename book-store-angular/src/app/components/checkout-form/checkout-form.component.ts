import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-checkout-form',
  templateUrl: './checkout-form.component.html',
  styleUrls: ['./checkout-form.component.css']
})
export class CheckoutFormComponent {

  @Output() cancel = new EventEmitter<boolean>();


  cancelCheckout() {
    this.cancel.emit(true)
  }
}
