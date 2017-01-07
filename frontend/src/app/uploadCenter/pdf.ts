/**
 * Created by Malte Scheller on 07.01.17.
 */
import {UploadedFile} from "./uploadedFile";

export class Pdf implements UploadedFile {

    name: string;
    id: string;
    file: File;


    constructor(name: string, id: string, file: File) {
        this.name = name;
        this.id = id;
        this.file = file;
    }
}