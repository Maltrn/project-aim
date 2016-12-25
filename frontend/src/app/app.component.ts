import {Component} from '@angular/core';
import {UserService} from "./authentication/user.service";

@Component({
  selector: 'app',
  templateUrl: './app.component.html',
})
export class AppComponent {

  constructor(private userService: UserService) {
    console.log(localStorage.getItem('user'));
  }
}
