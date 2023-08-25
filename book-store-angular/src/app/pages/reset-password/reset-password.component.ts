import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { tap } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent {

  constructor(private _auth: AuthService,
    private _router: Router) { }

  email: string = "";
  code = null;
  showRestInput = false;


  sendResetToken() {
    if (this.email) {
      this._auth.sendResetToken(this.email).subscribe();
    }
  }

  annuler() {
    this._router.navigateByUrl('/login')
  }
}
