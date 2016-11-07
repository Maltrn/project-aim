/**
 * Created by Dustin Spallek on 07.11.2016.
 */
import { Component } from '@angular/core';
import {FileUploadService} from '../file-upload-service/file-upload.service';

@Component({
    selector: 'fileupload-view',

    templateUrl: 'app/fileAdministration/file-upload/file-upload.component.html',

    providers: [FileUploadService],
})
export class UploadFileComponent{
    title = "File Upload Center";
    filenames;

    constructor(fileUploadService: FileUploadService){
        this.filenames = fileUploadService.getFileNames();
    }
}