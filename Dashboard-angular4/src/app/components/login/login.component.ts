import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserService} from '../../services/user.service';
import {NgForm} from '@angular/forms';
import {Router} from '@angular/router';

declare const gapi: any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  showErrorMessage = false;

  public auth2: any;

  constructor(private userService: UserService, private http: HttpClient, private router: Router) {}

  ngOnInit() {}

  public googleInit() {
    gapi.load('auth2', () => {
      this.auth2 = gapi.auth2.init({
        client_id: '743988849298-93ivuok2t10fh6crilmtdkagp8rhlfs0.apps.googleusercontent.com',
        cookiepolicy: 'single_host_origin',
        scope: 'profile email'
      });
      this.attachSignin(document.getElementById('googleBtn'));
    });
  }

  public attachSignin(element) {
    this.auth2.attachClickHandler(element, {},
      (googleUser) => {
        let token = googleUser.getAuthResponse().id_token;
        let profile = googleUser.getBasicProfile();
        console.log('Token || ' + token);
        console.log('ID: ' + profile.getId());
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail());

        this.http.post('http://localhost:8080/googlelogin', googleUser.getAuthResponse().id_token)
          .subscribe(data =>{
            this.userService.loginGoogleUser(token);
            this.showErrorMessage = false;
            this.router.navigateByUrl('');
          }, err => {
            console.error('Token cannot be validated on server side');
          });

      }, (error) => {
        alert(JSON.stringify(error, undefined, 2));
      });
  }

  ngAfterViewInit(){
    this.googleInit();
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
}
