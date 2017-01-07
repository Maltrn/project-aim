import {Component, ViewChild, ElementRef, OnInit, Sanitizer} from "@angular/core";
import {FileService} from "./file.service";
import {Settings} from "../app.config";
import {UserService} from "../authentication/user.service";
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";
import {Response, Http, RequestOptions, Headers} from "@angular/http";
import {Picture} from "./picture";
import {Pdf} from "./pdf";

@Component({
    selector: 'uploadCenter',
    templateUrl: './uploadCenterView.html'
})

export class UploadCenter implements OnInit {
    title: string = 'Dateiverwaltung';
    description: string = 'Übersicht aller hochgeladenen Dateien';

    private files;
    private pictures;
    private pdfs;
    private filesToUpload;

    private resizedPictures;

    fileUrl: SafeResourceUrl;
    resizedUrl: SafeResourceUrl;

    constructor(private fileService: FileService, private settings: Settings, private userService: UserService, private http: Http, private sanitizer: DomSanitizer) {
        this.files = [];
        this.pictures = [];
        this.pdfs = [];
        this.filesToUpload = [];
        this.resizedPictures = [];
    }

    ngOnInit(): void {
        this.loadFiles();
    }

    private loadFiles() {

        this.fileService.getAllFileIds().subscribe(
            data => {
                var fileIds = data;
                this.fetchAllFiles(fileIds);
            },
            error => {
                console.log("ERROR in REST API");
                if(error.indexOf("401") !== -1) {
                    this.userService.logout();
                }
            });
    }

    private fetchAllFiles(fileIds) {

        let reader = new FileReader();
        console.log(fileIds);

        for(let fileId of fileIds) {

            this.fileService.getFile(fileId).subscribe(
                response => {
                    if(response.json().type === 'image/jpeg' ||
                        response.json().type === 'image/bmp' ||
                        response.json().type === 'image/png') {

                        this.saveAsPicture(response.json(), fileId, reader);

                    } else {
                        this.saveAsPdf(response.json(), fileId, reader);
                    }
                },
                error => {
                    console.log("ERROR in REST API");
                    if(error.indexOf("401") !== -1) {
                        this.userService.logout();
                    }
                }
            );
        }
    }

    private saveAsPicture(blob: Blob, fileId, reader: FileReader)
    {
        let picture: Picture;
        let file: File;

        reader.onload = () => {
            file = reader.result;
            picture = new Picture("name", fileId, file);
            this.files.push(picture);
            this.pictures.push(picture);
        };
        reader.readAsDataURL(blob);
    }

    private saveAsPdf(blob: Blob, fileId, reader: FileReader)
    {
        let pdf: Pdf;
        let file: File;

        reader.onload = () => {
            file = reader.result;
            pdf = new Pdf("name", fileId, file);
            this.pdfs.push(pdf);
            this.files.push(pdf);
        };
        reader.readAsDataURL(blob);
    }

    public fileChange(input) {

        console.log(input.files[0]);

        for(let file of input.files)
            this.filesToUpload.push(file);
    }

    public upload() {

        for(let file of this.filesToUpload)
            this.fileService.uploadFile(file);
    }

    private replaceFile(fileToReplace, i)
    {
        console.log("Replace " + this.files[i].id + " with:");
        console.log(fileToReplace.files[0]);

        this.fileService.replace(fileToReplace.files[0], this.files[i].id);
    }

    private getImgSize(fileIndex)
    {
        var head = 'data:image/png;base64,';
        return Math.round(((this.files[fileIndex].file.length - head.length)*3/4)/1024);
    }

    public testMethod(fileInput) {
    }

    private showPicture(index)
    {
        window.open(this.files[index].file);
    }

    public reload() {

        this.files = [];
        this.pictures = [];
        this.pdfs = [];
        this.loadFiles();
    }
}
