import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { EMPTY, Observable, catchError } from "rxjs";
import { AuthService } from "../services/auth.service";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { LoadingService } from "../services/loading.service";
import { NotificationService } from "../services/notification.service";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

    constructor(private _auth: AuthService,
        private _router: Router,
        private _loading: LoadingService,
        private _notification: NotificationService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req).pipe(
            catchError(error => {
                this._loading.hideLoading();
                if (error.status === 403) {
                    this._auth.logout();
                    return EMPTY;
                }
                this._notification.showNotification(error.error.message)
                return EMPTY;
            })
        )
    }

}