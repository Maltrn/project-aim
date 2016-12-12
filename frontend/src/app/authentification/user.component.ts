import {Component} from "@angular/core";
import {UserService} from "./user.service.ts";
import {Router} from "@angular/router";
import {LoginRequest} from "./model/loginRequest";
import {Response} from "@angular/http";

@Component
({
    selector: 'login',
    templateUrl: './view/userview.component.html',
})

export class UserLogin
{
    constructor(private userService: UserService, private router: Router){}

    /**
     *
     * @param email
     * @param password
     */
    onSubmit(email, password)
    {
        var loginDto: LoginRequest =
        {
            password: password,     //TODO Eingaben mit Regex ueberpruefen
            username: email
        };

        this.userService.login(loginDto);
    }

    test1(): void
    {
        console.log(localStorage.getItem('aim_token'));
    }

    test2(): void
    {
        console.log(localStorage.getItem('aim_vendorId'));
    }

    logout(): void
    {
        console.log(this.userService.logout());
    }
}