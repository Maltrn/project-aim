import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {rootRouterConfig} from "./app.routes";
import {AppComponent} from "./app.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserModule} from "@angular/platform-browser";
import {HttpModule} from "@angular/http";
import {CKEditorModule} from "ng2-ckeditor";
import {UserService} from "./authentication/user.service";
import {UserLogin} from "./authentication/user.component";
import {AddFilesComponent} from "./informationComponents/add-files/add-files.component";
import {DetailedDescriptionComponent} from "./informationComponents/detailed-description/detailed-description.component";
import {FactsTableComponent} from "./informationComponents/facts/facts.component";
import {VendorInfoMain} from "./informationComponents/main/vendor-info-main.component";
import {ProductInfoMain} from "./informationComponents/main/product-info-main.component";
import {VendorService} from "./informationComponents/main/vendor.service";
import {ProductService} from "./informationComponents/main/product.service";
import {ProfilePictureService} from "./informationComponents/profile-pic/profile-pic.service";
import {ProfilePicComponent} from "./informationComponents/profile-pic/profile-pic.component";
import {ShortDiscriptionComponent} from "./informationComponents/short-description/short-discription.component";
import {SortFilesComponent} from "./informationComponents/sort-files/sort-files.component";
import {FileService} from "./uploadCenter/file.service";
import {UploadCenter} from "./uploadCenter/uploadCenter";
import {AuthGuard} from "./authentication/user.authguard";
import {Settings} from "./app.config";
import {FileSizePipe} from "./uploadCenter/file-size.pipe";

@NgModule({
  declarations: [
    AppComponent,
    UserLogin,
    AddFilesComponent,
    DetailedDescriptionComponent,
    FactsTableComponent,
    VendorInfoMain,
    ProductInfoMain,
    ShortDiscriptionComponent,
    SortFilesComponent,
    ProfilePicComponent,
    UploadCenter,
    FileSizePipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    RouterModule.forRoot(rootRouterConfig),
    CKEditorModule
  ],
  providers: [
    UserService,
    VendorService,
    ProductService,
    ProfilePictureService,
    FileService,
    AuthGuard,
    Settings
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

}
