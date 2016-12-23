import {Component} from "@angular/core";
import {UserService} from "./user.service";

@Component
({
  selector: 'login',
  templateUrl: './userview.component.html',
})

export class UserLogin {
  private userDTO = {};

  constructor(private userService: UserService) {
  }

  public login(username: string, password: string) {
    //this.userService.login().subscribe(
    //data => this.user = data,
    //error => console.log("ERROR in REST API")
    //);

    this.userService.login().subscribe(
      data => this.userDTO = data,
      error => console.log("ERROR in REST API")
    );

    localStorage.setItem('user', JSON.stringify(this.userDTO));


    console.log(localStorage.getItem('user'));
  }
}
