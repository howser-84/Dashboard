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
import {AuthService} from './services/auth.service';
import {AuthInterceptor} from './classes/AuthInterceptor';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'login', component: LoginComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    TodoInputComponent,
    TodoItemComponent,
    HomeComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [
    TodoService,
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      /*useFactory: function(router: Router, authService: AuthService) {
        return new AuthInterceptor(router, authService);
      },*/
      useClass: AuthInterceptor,
      multi: true,
      deps: [Router, AuthService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
