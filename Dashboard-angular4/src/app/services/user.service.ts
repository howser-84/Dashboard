import { Injectable } from '@angular/core';

@Injectable()
export class UserService {

  private username: String;
  private password: String;
  private logged: boolean;

  constructor() {
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
