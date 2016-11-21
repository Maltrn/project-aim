import {Component} from "@angular/core";
import {UserService} from "./user.service.ts";
//import { Router } from '@angular/router-deprecated';

@Component
({
    selector: 'login',
    templateUrl: './userview.component.html',
})
export class UserLogin {
    constructor(private userService: UserService, private router: Router) {}

    onSubmit(email, password) {
        this.userService.login(email, password).subscribe((result) => {
            if (result) {
                this.router.navigate(['Home']);
            }
        });
    }
}