import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private username: String;
  private password: String;

  constructor(private authService: AuthService, private http: HttpClient) {
    this.username = '';
    this.password = '';
  }

  ngOnInit() {
  }

  private login(): void{
    this.http.post('http://localhost:8080/login', {username: this.username, password: this.password})
      .subscribe(data => {console.log('Login OK'); this.authService.loginUser(this.username, this.password);}, err => {console.error('Something went very wrong.'+ err)});
  }

  private register(): void{
    this.http.post('http://localhost:8080/register', {username: this.username, password: this.password})
      .subscribe(data => {console.log('Login OK'); this.authService.loginUser(this.username, this.password);}, err => {console.error('Something went very wrong.'+ err)});
  }

}
