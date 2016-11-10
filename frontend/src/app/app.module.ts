import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {AppComponent} from "./app";
import {rootRouterConfig} from "./app.routes";
import {VendorAddFiles} from "./vendorInformation/add-files/add-files.component";
import {VendorDetailedDiscription} from "./vendorInformation/detailed-description/vendor-detailed-discription.component";
import {VendorNotes} from "./vendorInformation/notes/vendor-notes";
import {VendorInfoMain} from "./vendorInformation/main/vendor-info-main.component";
import {VendorShortDiscription} from "./vendorInformation/short-description/vendor-short-discription.component";
import {VendorProfilePic} from "./vendorInformation/profile-pic/vendor-profile-pic.component";
import {VendorSortFiles} from "./vendorInformation/sort-files/vendor-sort-files.component";
import {HttpModule} from "@angular/http";
import {InMemoryWebApiModule} from "angular-in-memory-web-api";
import {InMemoryDataService} from "./vendorInformation/backend-semu/in-memory-data.service";
import {RouterModule} from "@angular/router";
import {UploadCenter} from "./uploadCenter/uploadCenter";
import {FileService} from "./uploadCenter/file.service";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        InMemoryWebApiModule.forRoot(InMemoryDataService),
        RouterModule.forRoot(rootRouterConfig)
    ],
    declarations: [
        AppComponent,
        VendorInfoMain,
        VendorAddFiles,
        VendorDetailedDiscription,
        VendorShortDiscription,
        VendorNotes,
        VendorProfilePic,
        VendorSortFiles,
        UploadCenter
    ],
    bootstrap: [AppComponent],
    providers: [FileService]
})

export class AppModule
{
}