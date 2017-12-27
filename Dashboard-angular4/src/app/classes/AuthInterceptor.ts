import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpResponse, HttpErrorResponse
} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';

export class AuthInterceptor implements HttpInterceptor{

  constructor(){}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    console.log('About to intercept a message ' + request.method);
    if (request.method === 'OPTIONS'){
      return next.handle(request);
    }

    request = request.clone({
      setHeaders: {
        Authorization: `Basic amFjZWs6amFjZWsxMjM=` //authorization for jacek:jacek123
      }
    });

    return next.handle(request).do((event: HttpEvent<any>) => {
      if (event instanceof HttpResponse) {
        console.log("Success response received");
      }
    }, (err: any) => {
      if (err instanceof HttpErrorResponse) {
        if (err.status === 401) {
          console.error("401 response received");
        }
      }
    });
  }
}
