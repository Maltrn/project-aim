import {Datei} from "./datei.interface";

export class FileUploadService {

    FileUploadService;

    dateien: Datei[] = [{name: "tolleDatei.pdf", date: "01.09.2015", size: 4},
        {name: "hundebabies.jpg", date: "12.12.2016", size: 12},
        {name: "witzigerLurch.gif", date: "23.08.2016", size: 48},
        {name: "Bauplan.pdf", date: "09.09.2015", size: 128}];

    getFiles(): Datei[] {
        return this.dateien;
    }

    fillFilesFromBackend(): void {

    }


}
