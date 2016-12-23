import {Routes} from "@angular/router";
import {UserLogin} from "./authentification/user.component";
import {UploadCenter} from "./uploadCenter/uploadCenter";
import {VendorInfoMain} from "./informationComponents/main/vendor-info-main.component";
import {ProductInfoMain} from "./informationComponents/main/product-info-main.component";
import {AuthGuard} from "./authentification/user.authguard";

export const rootRouterConfig: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'upload-center', component: UploadCenter, canActivate: [AuthGuard]},
  {path: 'vendor-info', component: VendorInfoMain, canActivate: [AuthGuard]},
  {path: 'product-info', component: ProductInfoMain, canActivate: [AuthGuard]},
  {path: 'login', component: UserLogin}
];

