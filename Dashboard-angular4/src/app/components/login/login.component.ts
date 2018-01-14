import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserService} from '../../services/user.service';
import {NgForm} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService, GoogleLoginProvider, SocialUser} from 'angular4-social-login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  showErrorMessage = false;

  private user : SocialUser;

  constructor(private userService: UserService, private http: HttpClient, private router: Router, private authService : AuthService) {}

  ngOnInit() {
    this.authService.authState.subscribe((user) => {
      this.user = user;
      console.log('Google user succesfully identified');
    });
  }

  private login(loginForm: NgForm): void{
    this.http.post('http://localhost:8080/login', {username: loginForm.value.username, password: loginForm.value.password})
      .subscribe(data => {
        console.log('Login OK');
        this.userService.loginUser(loginForm.value.username, loginForm.value.password);
        this.showErrorMessage = false;
        this.router.navigateByUrl('');
        }
      , err => {console.error('Something went very wrong.' + err); this.showErrorMessage = true; });
  }

  private loginWithGoogle(): void{
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
  }

}
