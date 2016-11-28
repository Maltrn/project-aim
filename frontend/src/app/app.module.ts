import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {HttpModule} from "@angular/http";

import {AppComponent} from "./app";
import {rootRouterConfig} from "./app.routes";

// Vendor-Info
import {AddFilesComponent} from "./informationComponents/add-files/add-files.component";
import {DetailedDiscriptionComponent} from "./informationComponents/detailed-description/detailed-discription.component.ts";
import {FactsTableComponent} from "./informationComponents/facts/facts.component";
import {VendorInfoMain} from "./informationComponents/main/vendor-info-main.component";
import {ShortDiscriptionComponent} from "./informationComponents/short-description/short-discription.component.ts";
import {ProfilePicComponent} from "./informationComponents/profile-pic/profile-pic.component.ts";
import {SortFilesComponent} from "./informationComponents/sort-files/sort-files.component.ts";
import {InMemoryWebApiModule} from "angular-in-memory-web-api";
import {ProfilePictureService} from "./informationComponents/profile-pic/profile-pic.service.ts";
import {InMemoryDataService} from "./informationComponents/profile-pic/in-memory-data.service";

// Product-Info
import {ProductInfoMain} from "./informationComponents/main/product-info-main.component";
import  {UserLogin} from "./authentification/user.component"

// Upload-Center
import {UploadCenter} from "./uploadCenter/uploadCenter";
import {FileService} from "./uploadCenter/file.service";
import {VendorService} from "./informationComponents/main/vendor.service";
import {UserService} from "./authentification/user.service";
import {LoggedInGuard} from "./authentification/loged-in.guard";



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
        ProductInfoMain,
        UserLogin
    ],
    bootstrap: [AppComponent],
    providers: [FileService,
                ProfilePictureService,
                VendorService,
                UserService,
                LoggedInGuard]
})

export class AppModule
{
}