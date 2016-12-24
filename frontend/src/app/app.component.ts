import {Component} from '@angular/core';
import {UserService} from "./authentification/user.service";

@Component({
  selector: 'app',
  templateUrl: './app.component.html',
})
export class AppComponent {

  constructor(private userService: UserService) {

  }
}
