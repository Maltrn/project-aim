import {Component, OnInit} from "@angular/core";
import {FileService} from "./file.service";
import {Settings} from "../app.config";
import {UserService} from "../authentication/user.service";
import {Picture} from "./picture";
import {Pdf} from "./pdf";
import {FileSizePipe} from "./file-size.pipe";
import {Http, RequestOptions, Headers} from "@angular/http";

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

    private choosenIllegalFileType: boolean;
    private illegalFiles;

    private uploadErrorHappens: boolean;
    private uploadErrorMessage = "Nicht alle Dateien wurden hochgeladen.";

    constructor(private fileService: FileService, private settings: Settings, private userService: UserService, private http: Http) {
        this.files = [];
        this.pictures = [];
        this.pdfs = [];
        this.filesToUpload = [];

        this.choosenIllegalFileType = false;
        this.illegalFiles = [];

        this.uploadErrorHappens = false;
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
                if(error.indexOf("401") != -1) {
                    this.userService.logout();
                }
            });
    }

    private fetchAllFiles(fileIds) {

        let reader = new FileReader();
        console.log("File IDs: ");
        console.log(fileIds);

        for(let fileId of fileIds) {

            this.fileService.getFile(fileId)
                .then(
                    response => {
                        if(this.settings.uploadCenterImageFileTypes.indexOf(response.type) != -1)
                            this.saveAsPicture(response, fileId, reader);
                        else
                            this.saveAsPdf(response, fileId, reader);
                    })
                .catch(
                    error => {
                        console.log("ERROR in REST API");
                        if(error.indexOf("401") !== -1) {
                            this.userService.logout();
                        }
                    });
        }
    }

    private saveAsPicture(blob: Blob, fileId, reader: FileReader) {

        let picture: Picture;
        let file: File;

        reader.onload = () => {
            file = reader.result;
            picture = new Picture("name", fileId, file, blob);
            this.files.push(picture);
            this.pictures.push(picture);
        };
        reader.readAsDataURL(blob);
    }

    private saveAsPdf(blob: Blob, fileId, reader: FileReader) {

        let pdf: Pdf;
        let file: File;

        reader.onload = () => {
            file = reader.result;
            pdf = new Pdf("name", fileId, file, blob);
            this.pdfs.push(pdf);
            this.files.push(pdf);
        };
        reader.readAsDataURL(blob);
    }

    private pickFile(input) {

        this.illegalFiles = [];
        this.choosenIllegalFileType = false;

        for(let file of input.files) {


            if(((this.settings.uploadCenterImageFileTypes.indexOf(file.type) !== -1) ||
                (this.settings.uploadCenterApplicationFileTypes.indexOf(file.type) !== -1)) &&
                this.hasCorrectSize(file.size)) {

                console.log("File Input: ");
                console.log(file);
                this.filesToUpload.push(file);
            }
            else {

                this.illegalFiles.push(file.name);
                this.choosenIllegalFileType = true;
            }
        }
    }

    private uploadFile() {

        if(this.filesToUpload.length > 0) {

            this.uploadErrorHappens = false;
            this.illegalFiles = [];

            for(let file of this.filesToUpload) {

                if(((this.settings.uploadCenterImageFileTypes.indexOf(file.type) !== -1) ||
                    (this.settings.uploadCenterApplicationFileTypes.indexOf(file.type) !== -1)) &&
                    this.hasCorrectSize(file.size)) {

                    console.log("upload file: ");
                    console.log(file);
                    this.fileService.uploadFile(file);
                }
                else {

                    this.uploadErrorHappens = true;
                    this.illegalFiles.push(file.name);
                };
            }
            this.filesToUpload = [];
            this.reload();
        }
    }

    private hasCorrectSize(fileSize) {
        console.log(fileSize);
        return (fileSize <= this.settings.uploadCenterMaxFileSize);
    }

    private replaceFile(replaceFile, fileId) {

        console.log("Replace file with ID: " + fileId + " with: ");
        console.log(replaceFile.files[0]);

        this.fileService.replace(replaceFile.files[0], fileId);

        this.reload();
    }

    private showImg(file)
    {
        var result;

        if(this.settings.uploadCenterApplicationFileTypes.indexOf(file.blob.type) !== -1)
            result = "img/pdf.png"
        else
            result = file.file;

        return result;
    }

    private deleteFile(fileId) {

        console.log("Delete " + fileId);

        this.fileService.deleteFile(fileId);
    }

    private getImgSize(fileIndex) {

        var fsp: FileSizePipe = new FileSizePipe();
        return fsp.transform(fileIndex, 2);
    }

    public testMethod(fileInput) {

        console.log("File Nr.1: ");
        console.log(this.files[0]);
        console.log("Files to upload");
        console.log(this.filesToUpload);

        console.log('TOKEN ' + JSON.parse(localStorage.getItem('user')).loginResponse.currentToken);
    }

    private showPicture(index) {

        window.open(this.files[index].file);
    }

    private isImg(fileType): boolean
    {
        console.log(fileType);
        console.log((this.settings.uploadCenterImageFileTypes.indexOf(fileType) !== -1));
        return (this.settings.uploadCenterImageFileTypes.indexOf(fileType) !== -1);
    }

    public reload() {

        this.files = [];
        this.pictures = [];
        this.pdfs = [];
        this.loadFiles();
    }
}
