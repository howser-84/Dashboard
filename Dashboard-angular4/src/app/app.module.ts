import { BrowserModule } from '@angular/platform-browser';
import {Injector, NgModule} from '@angular/core';
import { FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule, Routes, Router } from '@angular/router';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { TodoInputComponent } from './components/todo-input/todo-input.component';
import {TodoService} from './services/todo.service';
import { TodoItemComponent } from './components/todo-item/todo-item.component';
import {AuthInterceptor} from './classes/AuthInterceptor';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import {UserService} from './services/user.service';
import {IsLoggedIn} from './classes/is-logged-in';
import { RegisterComponent } from './components/register/register.component';

import { SocialLoginModule, AuthServiceConfig } from 'angular4-social-login';
import { GoogleLoginProvider } from 'angular4-social-login';

const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [IsLoggedIn]},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent}
];

const config = new AuthServiceConfig([
  {
    id: GoogleLoginProvider.PROVIDER_ID,
    provider: new GoogleLoginProvider('743988849298-93ivuok2t10fh6crilmtdkagp8rhlfs0.apps.googleusercontent.com')
  }
]);

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    TodoInputComponent,
    TodoItemComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    SocialLoginModule.initialize(config)
  ],
  providers: [
    TodoService,
    UserService,
    IsLoggedIn,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
      deps: [Router, UserService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
