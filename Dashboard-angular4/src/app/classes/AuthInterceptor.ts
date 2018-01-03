import {Injectable, Injector} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpResponse, HttpErrorResponse
} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/observable/of';
import {Router} from '@angular/router';
import {AuthService} from '../services/auth.service';
import {UserService} from '../services/user.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor{

  constructor(private router: Router, private userService: UserService){}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    console.log('About to intercept a message ' + request.method);
    console.log('Checking if user is logged: ' + this.userService.isLogged());
    if (request.method === 'OPTIONS'){
      return next.handle(request);
    }

    if (this.userService.isLogged()){
      request = request.clone({
        setHeaders: {
          Authorization: `Basic ` + this.userService.getEncodedUserCredentials()
        }
      });
    }

    return next.handle(request).do((event: HttpEvent<any>) => {
      if (event instanceof HttpResponse) {
        console.log("Success response received");
        this.router.navigateByUrl('');
      }
    }, (err: any) => {
      //big assumption here - any errorneous responses will be treated as 401
      //due to CORS mechanisms i'm unable to check status of error response and keep getting status 0 instead of 401
      this.router.navigateByUrl('login');
    });
  }
}
