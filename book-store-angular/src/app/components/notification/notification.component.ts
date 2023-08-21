import { Component } from '@angular/core';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent {


  constructor(private _notification: NotificationService) { }

  notification$ = this._notification.notifcation$;


  dismiss() {
    this._notification.clearNotification();
  }
}
