import {UploadedFile} from "./uploadedFile";

export class Picture implements UploadedFile {

    name: string;
    id: string;
    file: File;


    constructor(name: string, id: string, file: File) {
        this.name = name;
        this.id = id;
        this.file = file;
    }
}