import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private _auth: AuthService) { }

  email: string | null = null;
  password: string | null = null;

  doLogin() {
    if (this.email && this.password) {
      this._auth.login({ email: this.email, password: this.password }).subscribe();
    }
  }
}
