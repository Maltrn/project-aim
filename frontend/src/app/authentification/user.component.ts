import {Component} from "@angular/core";
import {UserService} from "./user.service";

@Component
({
  selector: 'login',
  templateUrl: './userview.component.html',
})

export class UserLogin {
  private loginResponse = {};
  private email: string;
  private password: string;

  constructor(private userService: UserService) {
  }

  public login(): void {
    this.userService.login(this.email, this.password).subscribe(
      data => {
        if (data != "" && data != []) {
          this.loginResponse = data;
          localStorage.setItem('user', JSON.stringify(data));
        } else {
          console.log("Login response was empty");
        }
      },
      err => {
        console.log(err);
      });
  }

  public logout(): void {
    localStorage.removeItem('user');
  }
}
