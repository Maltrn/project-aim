import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {HttpModule} from "@angular/http";

import {AppComponent} from "./app";
import {rootRouterConfig} from "./app.routes";

// Vendor-Info
import {AddFilesComponent} from "./frontendComponents/add-files/add-files.component";
import {DetailedDiscriptionComponent} from "./frontendComponents/detailed-description/detailed-discription.component.ts";
import {FactsTableComponent} from "./frontendComponents/facts/facts.component";
import {VendorInfoMain} from "./frontendComponents/main/vendor-info-main.component";
import {ShortDiscriptionComponent} from "./frontendComponents/short-description/short-discription.component.ts";
import {ProfilePicComponent} from "./frontendComponents/profile-pic/profile-pic.component.ts";
import {SortFilesComponent} from "./frontendComponents/sort-files/sort-files.component.ts";
import {InMemoryWebApiModule} from "angular-in-memory-web-api";
import {InMemoryDataService} from "./frontendComponents/backend-semu/in-memory-data.service";
import {ProfilePictureService} from "./frontendComponents/profile-pic/profile-pic.service.ts";

// Product-Info
import {ProductInfoMain} from "./frontendComponents/main/product-info-main.component";

// Upload-Center
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
        AddFilesComponent,
        DetailedDiscriptionComponent,
        ShortDiscriptionComponent,
        FactsTableComponent,
        ProfilePicComponent,
        SortFilesComponent,
        UploadCenter,
        ProductInfoMain
    ],
    bootstrap: [AppComponent],
    providers: [FileService,
                ProfilePictureService]
})

export class AppModule
{
}