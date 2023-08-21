import { Component } from '@angular/core';
import { RegisterRequest } from 'src/app/models/registerRequest.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  constructor(private _auth: AuthService) { }
  firstName: string | null = null
  lastName: string | null = null;
  gender: string | null = null;
  email: string | null = null;
  password: string | null = null;

  register() {
    if (this.email && this.password && this.firstName && this.lastName && this.gender) {
      let request: RegisterRequest = { email: this.email, password: this.password, firstName: this.firstName, lastName: this.lastName, gender: this.gender }
      this._auth.register(request).subscribe();
    }
  }
}