import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { tap } from 'rxjs';
import { ResetPassword } from 'src/app/models/resetPassword.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent {
  constructor(private _auth: AuthService,
    private _router: Router) { }

  token: string = "";
  new_password = null;
  new_password_check = null;
  error = false;
  error_message = "";


  resetPassword() {
    if (!this.new_password || !this.new_password_check || !this.token) {
      this.error_message = "please provide all fields";
      this.error = true;
      return;
    }
    if (this.new_password !== this.new_password_check) {
      this.error_message = "password doesn't match";
      this.error = true;
      return;
    }
    let reset: ResetPassword = { token: this.token, new_password: this.new_password!, new_password_check: this.new_password_check! };
    console.log(reset)
    this._auth.resetPassword(reset).subscribe();
  }

  annuler() {
    this._router.navigateByUrl('/login')
  }
}
