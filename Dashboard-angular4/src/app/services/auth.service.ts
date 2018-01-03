import {Injectable} from '@angular/core';
import {UserService} from './user.service';

@Injectable()
export class AuthService {

  constructor(private userService: UserService){}

}
