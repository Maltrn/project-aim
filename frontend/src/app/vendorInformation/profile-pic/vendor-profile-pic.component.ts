import {Component, OnInit} from "@angular/core";
import {FileService} from "./file.service";

@Component
({
    selector: 'vendor-info-profile-pic',
    templateUrl: './vendor-profile-pic.component.html',
    styleUrls: []
})

/*TODO FileService verwenden um Files in der GUI auszulisten*/
export class VendorProfilePic implements OnInit {
    title: string = 'Profil-Bild';

    pictureIds: string[] = [];
    selectedPicture: string = 'Noch keine Bild gewählt';

    constructor(private fileService: FileService) {
    }

    ngOnInit(): void {
        this.getFileIDs();
    }

    getFileIDs(): void {
        this.fileService.getPictureIdsOff().then(recIds => this.sortID(recIds));
    }

    sortID(fileIds: string[]): void {
        var accu: string[] = [];

        for (let id of fileIds) {
            if (id.match(new RegExp('[a-zA-Z0-9].jpg')))
                accu.push(id);
        }

        if (accu.length > 0)
            this.pictureIds = accu;
        else
            this.pictureIds.push('Keine Bilder gefunden.');
    }

    onSelect(id: string): void {
        alert(id);
        this.selectedPicture = id;
    }
}