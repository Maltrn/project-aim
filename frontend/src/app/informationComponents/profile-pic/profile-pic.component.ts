import {Component} from "@angular/core";
import {ProfilePictureService} from "./profile-pic.service";
import {FileID} from "../main/model/fileId";
import {File} from "../main/model/file";
import {Response} from "@angular/http";

const errMsg401 = 'Der Token ist ungültig';
const errMsg404 = 'Es wurde keine Datei mit der angegebenen ObjectId gefunden';
const unknownErrMsg = 'Unbekannter Fehler';

@Component
({
    selector: 'vendor-info-profile-pic',
    templateUrl: './view/profile-pic.component.html',
    styleUrls: ['./view/profile-pic.component.css']
})

export class ProfilePicComponent
{
  /**
  title: string = 'Profil-Bild';
    description: string = 'Wählen Sie Ihr gewünschtes Profilbild aus';

    pictures: File[] = [];
    selectedPicture: string = 'Noch kein Bild gewählt';

    picDataTypesRegEx = /(png)$|(jpg)$|(gif)$|(jpeg)$/;

    constructor(private profilePicService: ProfilePictureService)
    {
    }

    getListofPictures(): void
    {
        this.profilePicService.loadFiles()
            .then(response => this.loadFiles(response))
            .then(allFiles => this.sortOutNonPictures(allFiles))
            .then(pictures => this.pictures = pictures)
            .catch(this.handleError);
    }

    private loadFiles(fileIds: FileID[]): File[]
    {
        var files: File[] = [];

        for(var file of fileIds)
        {
            this.profilePicService.getSpecificFile(file.item).then(response => files.push(response));
        }

        return files;
    }

    private sortOutNonPictures(files: File[]): File[]
    {
        var pictures: File[] = [];

        for(var file of files)
        {
            if(file.name.match(this.picDataTypesRegEx))
                pictures.push(file);
        }

        return pictures;
    }

    test(): void
    {
        var files: File[] = [];

        files = this.profilePicService.getPicture();

        for(var file of files)
        {
            if(file.name.match(this.picDataTypesRegEx))
                this.pictures.push(file);
        }
    }

    onSelect(id: string): void
    {
        this.selectedPicture = id;
    }

    private handleError(error: any): void
    {
        let errMsg: string;

        if(error instanceof Response)
        {
            let resErr: Response = error;
            resErr.status;
            errMsg = `${resErr.status} - ${resErr.statusText || ''} ${resErr}`;

            switch(resErr.status)
            {
                case 401:
                    errMsg = errMsg401;
                    break;
                case 404:
                    errMsg = errMsg404;
                    break;
                default:
                    errMsg = unknownErrMsg;
            }
        }
        else
        {
            errMsg = error.toString();
        }

        console.error('An error occurred in Profile-Pic-Service', errMsg);

        if((errMsg !== '') && (errMsg !== null))      //TODO ErrorMsg direkt im Eingabebereich anzeigen. PopUp evtl.
            alert(errMsg);
        else
            alert('Fehler nicht beschrieben');        //TODO Beschreibung anpassen
    }
   */
}
