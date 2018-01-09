import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../services/user.service';
import {HttpClient} from '@angular/common/http';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  showErrorMessage = false;

  constructor(private userService: UserService, private http: HttpClient, private router: Router) {}

  ngOnInit() {
  }

  private register(registerForm: NgForm): void{
    this.http.post('http://localhost:8080/register', {username: registerForm.value.username, password: registerForm.value.password})
      .subscribe(data => {
          console.log('Registration OK');
          this.showErrorMessage = false;
          this.router.navigateByUrl('login');
        }
        , err => {console.error('Something went very wrong.' + err); this.showErrorMessage = true; });
  }
}
