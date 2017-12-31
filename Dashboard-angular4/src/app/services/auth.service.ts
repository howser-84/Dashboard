import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class AuthService {

  private username: String;
  private password: String;
  private logged: boolean;

  constructor() {
    //this.username = 'jacek';
    //this.password = 'jacek123';
    //this.logged = true;
    this.username = '';
    this.password = '';
    this.logged = false;
  }

  public loginUser(username: String, password: String): void {
    this.username = username;
    this.password = password;
    this.logged = true;
  }

  public getEncodedUserCredentials(): String {
    return btoa(this.username + ':' + this.password);
  }

  public isLogged(): boolean{
    return this.logged;
  }

}
