import {UploadedFile} from "./uploadedFile";

export class Picture implements UploadedFile {

    name: string;
    id: string;
    file: File;
    blob: Blob;

    constructor(name: string, id: string, file: File, blob: Blob) {
        this.name = name;
        this.id = id;
        this.file = file;
        this.blob = blob;
    }
}