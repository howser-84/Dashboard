import { Injectable } from '@angular/core';

@Injectable()
export class UserService {

  private logged: boolean;
  private googleUser : boolean;

  constructor() {
    this.logged = false;
    this.googleUser = false;
  }

  public loginUser(username: string, password: string): void {
    this.logged = true;
    localStorage.setItem('username', username);
    localStorage.setItem('password', password);
  }

  public loginGoogleUser(token: string): void{
    this.logged = true;
    this.googleUser = true;
    localStorage.setItem('token', token);
  }

  public getEncodedUserCredentials(): String {
    return btoa(this.getUsername() + ':' + this.getPassword());
  }

  public isLogged(): boolean{
    return this.logged;
  }

  public isGoogleLogged(): boolean{
    return this.googleUser;
  }

  private getUsername(): string{
    return localStorage.getItem('username');
  }

  private getPassword(): string{
    return localStorage.getItem('password');
  }

  private getToken(): string{
    return localStorage.getItem('token');
  }
}
