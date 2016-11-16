import {Component, OnInit} from "@angular/core";
import {ProfilePictureService} from "./profile-pic.service.ts";

@Component
({
    selector: 'vendor-info-profile-pic',
    templateUrl: './profile-pic.component.html',
    styleUrls: []
})

/*TODO ProfilePictureService verwenden um Files in der GUI auszulisten*/
export class ProfilePicComponent implements OnInit {
    title: string = 'Profil-Bild';
    description: string = 'Wählen Sie Ihr gewünschtes Profilbild aus'

    pictureIds: string[] = [];
    selectedPicture: string = 'Noch keine Bild gewählt';

    constructor(private profilePicService: ProfilePictureService) {
    }

    ngOnInit(): void {
        this.getFileIDs();
    }

    getFileIDs(): void {
        this.profilePicService.getPictureIdsOff().then(recIds => this.sortID(recIds));
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