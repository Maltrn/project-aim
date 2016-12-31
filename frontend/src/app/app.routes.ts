import {Routes} from "@angular/router";
import {UserLogin} from "./authentication/user.component";
import {UploadCenter} from "./uploadCenter/uploadCenter";
import {VendorInfo} from "./vendor/vendorInfo.component";
import {ProductInfoMain} from "./informationComponents/main/product-info-main.component";
import {AuthGuard} from "./authentication/user.authguard";

export const rootRouterConfig: Routes = [
  {path: '', redirectTo: 'vendor-info', pathMatch: 'full'},
  {path: 'upload-center', component: UploadCenter, canActivate: [AuthGuard]},
  {path: 'vendor-info', component: VendorInfo, canActivate: [AuthGuard]},
  {path: 'product-info', component: ProductInfoMain, canActivate: [AuthGuard]},
  {path: 'login', component: UserLogin}
];

