import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserService} from '../../services/user.service';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  showErrorMessage = false;

  constructor(private userService: UserService, private http: HttpClient) {}

  ngOnInit() {
  }

  private login(loginForm: NgForm): void{
    this.http.post('http://localhost:8080/login', {username: loginForm.value.username, password: loginForm.value.password})
      .subscribe(data => {console.log('Login OK'); this.userService.loginUser(loginForm.value.username, loginForm.value.password); this.showErrorMessage = false; }
      , err => {console.error('Something went very wrong.' + err); this.showErrorMessage = true; });
  }

}
