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
import {VendorInfo} from "./vendor/vendorInfo.component";
import {ProductInfo} from "./product/productInfo.component";
import {VendorService} from "./vendor/vendor.service";
import {ProductService} from "./product/product.service";
import {FileService} from "./uploadCenter/file.service";
import {UploadCenter} from "./uploadCenter/uploadCenter";
import {AuthGuard} from "./authentication/user.authguard";
import {Settings} from "./app.config";
import {FileSizePipe} from "./uploadCenter/file-size.pipe";
import {KeyPipe, ValuePipe} from "./vendor/keyValuePipe";
import {NavigationComponent} from "./navigation/navigation.component";
import {CollapseModule, DropdownModule} from "ng2-bootstrap";

@NgModule({
  declarations: [
    AppComponent,
    UserLogin,
    VendorInfo,
    ProductInfo,
    NavigationComponent,
    UploadCenter,
    FileSizePipe,
    KeyPipe,
    ValuePipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    RouterModule.forRoot(rootRouterConfig),
    CKEditorModule,
    CollapseModule,
    DropdownModule
  ],
  providers: [
    UserService,
    VendorService,
    ProductService,
    FileService,
    AuthGuard,
    Settings
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

}
