/**
 * Created by Dustin Spallek on 07.11.2016.
 */
import { Component } from '@angular/core';
import {FileUploadService} from '../fileAdministration-service/file-upload.service';

@Component({
    selector: 'filedatatable-view',

    templateUrl: 'app/fileAdministration/file-datatable/file-datatable.component.html',

    providers: [FileUploadService],
})
export class FileDatatableComponent{
    title = "File Datatable";
    files;

    constructor(fileUploadService: FileUploadService){
        this.files = fileUploadService.getFiles();
    }
}