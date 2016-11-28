import {Component} from "@angular/core";
import {UserService} from "./user.service.ts";
import {Router} from "@angular/router";
import {LoginDTO} from "./model/loginDTO";

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
            .catch(response => this.handleError(response));
    }

    handleError(error: any): void
    {
        let msg: string;

        switch(error)
        {
            case 401:
                msg = errMsg401;
                break;
            case 503:
                msg = errMsg503;
                break;
            default:
                msg = unknownErrMsg;
        }

        this.showErrMsg(msg);
    }

    /**
     * Shows an error message.
     *
     * @param msg
     */
    showErrMsg(msg: string): void
    {
        if((msg !== '') && (msg !== null))      //TODO ErrorMsg direkt im Eingabebereich anzeigen. PopUp evtl.
            alert(msg);
        else
            alert('Fehler nicht beschrieben');  //TODO Beschreibung anpassen
    }
}