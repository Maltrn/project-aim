import {Component} from "@angular/core";
import {UserService} from "./user.service.ts";
import {Router} from "@angular/router";
import {LoginDTO} from "./model/loginDTO";
import {Response} from "@angular/http";

const errMsg401 = 'Benutzername existiert nicht oder das angegebene Passwort ist falsch';
const errMsg503 = 'Login nicht erfolgreich, da keine Anbieter und/oder Produktinformationen abgerufen werden können.';
const unknownErrMsg = 'Unbekannter Fehler';

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
        var loginDto: LoginDTO =
        {
            password: password,     //TODO Eingaben mit Regex ueberpruefen
            username: email
        };

        this.userService.login(loginDto)
            .catch(this.handleError);
    }

    handleError(error: any): void
    {
        let errMsg: string;

        if(error instanceof Response)
        {
            let resErr: Response = error;
            errMsg = `${resErr.status} - ${resErr.statusText || ''} ${resErr}`;

            switch(resErr.status)
            {
                case 401:
                    errMsg = errMsg401;
                    break;
                case 503:
                    errMsg = errMsg503;
                    break;
                default:
                    errMsg = unknownErrMsg;
            }
        }
        else
        {
            errMsg = error.toString();
        }

        console.error('An error occurred in User-Service', errMsg);

        if((errMsg !== '') && (errMsg !== null))      //TODO ErrorMsg direkt im Eingabebereich anzeigen. PopUp evtl.
            alert(errMsg);
        else
            alert('Fehler nicht beschrieben');  //TODO Beschreibung anpassen
    }
}