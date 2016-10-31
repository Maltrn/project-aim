/**
 * Created by Maltron on 31.10.2016.
 */

import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule} from "@angular/forms";


import {AppComponent} from "./appView/app.component";
import {VendorAddFiles} from "./vendorInformation/add-files/add-files.component";
import {VendorDetailedDiscription} from "./vendorInformation/detailed-description/vendor-detailed-discription.component";
import {VendorNotes} from "./vendorInformation/notes/vendor-notes";
import {VendorInfoMain} from "./vendorInformation/main/vendor-info-main.component";
import {VendorShortDiscription} from "./vendorInformation/short-description/vendor-short-discription.component";
import {VendorProfilePic} from "./vendorInformation/profile-pic/vendor-profile-pic.component";
import {VendorSortFiles} from "./vendorInformation/sort-files/vendor-sort-files.component";

@NgModule({
    imports:      [ BrowserModule,
                    FormsModule ],
    declarations: [ AppComponent,
                    VendorInfoMain,
                    VendorAddFiles,
                    VendorDetailedDiscription,
                    VendorShortDiscription,
                    VendorNotes,
                    VendorProfilePic,
                    VendorSortFiles
                    ],
    bootstrap:    [ AppComponent ]
})

export class AppModule { }
