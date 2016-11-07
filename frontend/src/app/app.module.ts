import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {AppComponent} from "./appView/app.component";
import {VendorAddFiles} from "./vendorInformation/add-files/add-files.component";
import {VendorDetailedDiscription} from "./vendorInformation/detailed-description/vendor-detailed-discription.component";
import {VendorNotes} from "./vendorInformation/notes/vendor-notes";
import {VendorInfoMain} from "./vendorInformation/main/vendor-info-main.component";
import {VendorShortDiscription} from "./vendorInformation/short-description/vendor-short-discription.component";
import {VendorProfilePic} from "./vendorInformation/profile-pic/vendor-profile-pic.component";
import {VendorSortFiles} from "./vendorInformation/sort-files/vendor-sort-files.component";
import {FileService} from "./vendorInformation/profile-pic/file.service";
import {HttpModule} from "@angular/http";
import {UploadFileComponent} from "./fileAdministration/file-upload/file-upload.component";
import {FileDatatableComponent} from "./fileAdministration/file-datatable/file-datatable.component";
import {InMemoryWebApiModule} from "angular-in-memory-web-api";
import {InMemoryDataService} from "./vendorInformation/backend-semu/in-memory-data.service";

// Imports for loading & configuring the in-memory web api


@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        InMemoryWebApiModule.forRoot(InMemoryDataService)
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
        UploadFileComponent,
        FileDatatableComponent,
    ],
    bootstrap: [AppComponent],
    providers: [FileService]
})

export class AppModule {
}
