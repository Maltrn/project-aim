import {Component} from "@angular/core";
import {UserService} from "./user.service";
import {Router} from "@angular/router";

@Component
({
  selector: 'login',
  templateUrl: './userview.component.html',
})

export class UserLogin {
  private loginResponse: any;
  private email: string;
  private password: string;
  private error: string = "";

  constructor(private userService: UserService, private router: Router) {
    if (localStorage.getItem('user') != null) {
      this.loginResponse = localStorage.getItem('user');
    }
  }

  public login(): void {
    this.userService.login(this.email, this.password).subscribe(
      data => {
        if (data != "" && data != []) {
          this.loginResponse = data;
          localStorage.setItem('user', JSON.stringify(data));
          this.error = "";
          this.router.navigate(['/vendor-info']);
        } else {
          console.log("Login response was empty");
        }
      },
      err => {
        this.error = "Login Daten sind ungültig";
        console.log(err);
      });
  }

  public logout(): void {
    localStorage.removeItem('user');
    this.loginResponse = null;
    this.error = "";
    this.router.navigate(['/login']);
  }
}
