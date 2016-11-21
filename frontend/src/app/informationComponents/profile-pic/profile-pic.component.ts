import {Component} from "@angular/core";
import {ProfilePictureService} from "./profile-pic.service.ts";
import {FileID} from "../main/model/fileId";
import {File} from "../main/model/file";

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
            .then(response => this.sortOutNonPictures(response))
            .then(response => this.pictures = response);
    }

    private getAllFiles(fileIds: FileID[]): File[]
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
}