import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor() { }


  private notifcation = new BehaviorSubject<string | null>(null);
  notifcation$ = this.notifcation.asObservable();

  showNotification(message: string) {
    this.notifcation.next(message);
  }

  clearNotification() {
    this.notifcation.next(null);
  }

}
