import {Component} from "@angular/core";
import {FileService} from "./file.service";
import {UploadedFile} from "./uploadedFile";

@Component({
    selector: 'uploadCenter',
    templateUrl: './uploadCenterView.html'
})
export class UploadCenter {
    protected files: UploadedFile[];

    constructor(private fileService: FileService) {
        this.files = fileService.getAllFiles();
    }
}