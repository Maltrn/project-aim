import {Component} from "@angular/core";
import {FileService} from "./file.service";
import {Settings} from "../app.config";
import {UserService} from "../authentication/user.service";

@Component({
  selector: 'uploadCenter',
  templateUrl: './uploadCenterView.html'
})
export class UploadCenter {

  protected files;

  title: string = 'Dateiverwaltung';

  description: string = 'Übersicht aller hochgeladenen Dateien';

  constructor(private fileService: FileService, private settings: Settings, private userService: UserService) {
    fileService.getAllFiles().subscribe(
      data => this.files = data,
      error => {
        console.log("ERROR in REST API");
        if (error.indexOf("401") !== -1) {
          this.userService.logout();
        }
      });
  }
}
