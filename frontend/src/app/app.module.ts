import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {rootRouterConfig} from "./app.routes";
import {AppComponent} from "./app.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserModule} from "@angular/platform-browser";
import {HttpModule} from "@angular/http";

import {UserService} from "./authentification/user.service";
import {UserLogin} from "./authentification/user.component";
import {AddFilesComponent} from "./informationComponents/add-files/add-files.component";
import {DetailedDiscriptionComponent} from "./informationComponents/detailed-description/detailed-discription.component";
import {FactsTableComponent} from "./informationComponents/facts/facts.component";
import {VendorInfoMain} from "./informationComponents/main/vendor-info-main.component";
import {ProductInfoMain} from "./informationComponents/main/product-info-main.component";
import {VendorService} from "./informationComponents/main/vendor.service";
import {ProductService} from "./informationComponents/main/product.service";
import {ProfilePictureService} from "./informationComponents/profile-pic/profile-pic.service";
import {ProfilePicComponent} from "./informationComponents/profile-pic/profile-pic.component";
import {InMemoryWebApiModule} from "angular2-in-memory-web-api";
import {MemService} from "./memService";
import {ShortDiscriptionComponent} from "./informationComponents/short-description/short-discription.component";
import {SortFilesComponent} from "./informationComponents/sort-files/sort-files.component";

@NgModule({
  declarations: [
    AppComponent,
    UserLogin,
    AddFilesComponent,
    DetailedDiscriptionComponent,
    FactsTableComponent,
    VendorInfoMain,
    ProductInfoMain,
    InMemoryWebApiModule.forRoot(MemService),
    ShortDiscriptionComponent,
    SortFilesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    RouterModule.forRoot(rootRouterConfig, { useHash: true })
  ],
  providers: [
    UserService,
    VendorService,
    ProductService,
    ProfilePictureService,
    ProfilePicComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

}
