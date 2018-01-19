import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/observable/of';
import {Router} from '@angular/router';
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

    if (this.userService.isGoogleLogged()){
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ` + this.userService.getToken()
        }
      });
    } else if (this.userService.isLogged()){
      request = request.clone({
        setHeaders: {
          Authorization: `Basic ` + this.userService.getEncodedUserCredentials()
        }
      });
    }

    return next.handle(request);
  }
}
