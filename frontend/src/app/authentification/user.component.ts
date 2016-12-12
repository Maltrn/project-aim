import {Component} from "@angular/core";
import {UserService} from "./user.service";
import {LoginDTO} from "./model/loginDTO";

@Component
({
  selector: 'login',
  templateUrl: './view/userview.component.html',
})

export class UserLogin
{
  private user = {};

  constructor(private userService: UserService)
  {
  }

  public login(username: string, password: string)
  {
    let loginBody: LoginDTO = new LoginDTO(username, password);
    this.userService.login().subscribe(
      data => this.user = data,
      error => console.log("ERROR in REST API")
    );
  }
}
