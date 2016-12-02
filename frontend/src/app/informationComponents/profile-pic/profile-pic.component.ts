import {Component} from "@angular/core";
import {ProfilePictureService} from "./profile-pic.service.ts";
import {FileID} from "../main/model/fileId";
import {File} from "../main/model/file";

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
        this.profilePicService.getAllFileIds()
            .then(response => this.getAllFiles(response))
            .then(allFiles => this.sortOutNonPictures(allFiles))
            .then(pictures => this.pictures = pictures)
            .catch(this.handleError);
    }

    private getAllFiles(fileIds: FileID[] | number): File[]
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

    handleError(error: number)
    {
        let msg: string;

        switch(error)
        {
            case 401:
                msg = errMsg401;
                break;
            case 404:
                msg = errMsg404;
                break;
            default:
                msg = unknownErrMsg;
        }

        this.showErrMsg(msg);
    }

    /**
     * Shows an error message.
     *
     * @param msg
     */
    showErrMsg(msg: string): void
    {
        if((msg !== '') && (msg !== null))      //TODO ErrorMsg direkt im Eingabebereich anzeigen. PopUp evtl.
            alert(msg);
        else
            alert('Fehler nicht beschrieben');  //TODO Beschreibung anpassen
    }
}