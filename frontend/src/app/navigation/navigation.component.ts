import {Component} from "@angular/core";
import {UserService} from "../authentication/user.service";

@Component
({
  selector: 'navigation',
  templateUrl: './navigation.component.html',
})
export class NavigationComponent {

  constructor(private userService: UserService) {

  }
}
