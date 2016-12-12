import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {HttpModule} from "@angular/http";
import {AppComponent} from "./app";
import {rootRouterConfig} from "./app.routes";
import {AddFilesComponent} from "./informationComponents/add-files/add-files.component";
import {DetailedDiscriptionComponent} from "./informationComponents/detailed-description/detailed-discription.component";
import {FactsTableComponent} from "./informationComponents/facts/facts.component";
import {VendorInfoMain} from "./informationComponents/main/vendor-info-main.component";
import {ShortDiscriptionComponent} from "./informationComponents/short-description/short-discription.component";
import {ProfilePicComponent} from "./informationComponents/profile-pic/profile-pic.component";
import {SortFilesComponent} from "./informationComponents/sort-files/sort-files.component";
import {InMemoryWebApiModule} from "angular-in-memory-web-api";
import {ProfilePictureService} from "./informationComponents/profile-pic/profile-pic.service";
import {InMemoryDataService} from "./informationComponents/profile-pic/in-memory-data.service";
import {ProductInfoMain} from "./informationComponents/main/product-info-main.component";
import {UserLogin} from "./authentification/user.component";
import {UploadCenter} from "./uploadCenter/uploadCenter";
import {FileService} from "./uploadCenter/file.service";
import {VendorService} from "./informationComponents/main/vendor.service";
import {ProductService} from "./informationComponents/main/product.service"
import {UserService} from "./authentification/user.service";
import {LoggedInGuard} from "./authentification/loged-in.guard";
import {Configs} from "./app.config";
import {CKEditorModule} from "ng2-ckeditor";
import {VendorData} from "./informationComponents/main/vendor-data";
import {MemService} from "./memService";

// Vendor-Info

// Product-Info

// Upload-Center


@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        InMemoryWebApiModule.forRoot(InMemoryDataService),
        InMemoryWebApiModule.forRoot(MemService),
        RouterModule.forRoot(rootRouterConfig),
        CKEditorModule
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
    providers: [
        FileService,
        ProfilePictureService,
        VendorService,
        ProductService,
        UserService,
        LoggedInGuard,
        Configs]
})

export class AppModule {
}