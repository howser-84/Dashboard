import { Injectable } from '@angular/core';

@Injectable()
export class UserService {

  private logged: boolean;

  constructor() {
    this.logged = false;
  }

  public loginUser(username: string, password: string): void {
    this.logged = true;
    localStorage.setItem('username', username);
    localStorage.setItem('password', password);
  }

  public getEncodedUserCredentials(): String {
    return btoa(this.getUsername() + ':' + this.getPassword());
  }

  public isLogged(): boolean{
    return this.logged;
  }

  private getUsername(): string{
    return localStorage.getItem('username');
  }

  private getPassword(): string{
    return localStorage.getItem('password');
  }
}
