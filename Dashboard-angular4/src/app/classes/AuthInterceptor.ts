import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpResponse, HttpErrorResponse
} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

export class AuthInterceptor implements HttpInterceptor{

  constructor(){}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    request = request.clone({
      setHeaders: {
        Authorization: `Basic amFjZWs6amFjZWsxMjM=` //authorization for jacek:jacek123
      }
    });

    return next.handle(request);
  }
}
