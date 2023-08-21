import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";
import { AuthResponse } from "../models/authResponse.model";
import { AuthService } from "../services/auth.service";
import { Injectable } from "@angular/core";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

    constructor(private _auth: AuthService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const jwt = this._auth.loadAuthFromLS()?.access_token;
        if (jwt) {
            return next.handle(req.clone({ setHeaders: { "Authorization": `Bearer ${jwt}` } }))
        }
        return next.handle(req)
    }

}