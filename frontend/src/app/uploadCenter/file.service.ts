import {Injectable} from "@angular/core";
import {UploadedFile} from "./uploadedFile";

@Injectable()
export class FileService {

    dateien: UploadedFile[] = [
        {id: "1", name: "tolleDatei.pdf", location: "asd"},
        {id: "2", name: "hundebabies.jpg", location: "asd"},
        {id: "3", name: "witzigerLurch.gif", location: "asd"},
        {id: "4", name: "Bauplan.pdf", location: "asd"}
    ];

    public getAllFiles(): UploadedFile[] {
        return this.dateien;
    }
}
