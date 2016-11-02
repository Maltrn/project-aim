/**
 * Created by Malte Scheller on 31.10.2016.
 */
import { Component } from '@angular/core'
import {FileService} from "./file.service";

@Component
({
    selector: 'vendor-info-profile-pic',
    templateUrl: 'app/vendorInformation/profile-pic/vendor-profile-pic.component.html'
})

/*TODO FileService verwenden um Files in der GUI auszulisten*/
export class VendorProfilePic
{
    title: string = 'Profil-Bild';

    constructor(private fileService: FileService)
    {
    }

    chooseProfilePic(): void
    {

    }
}