import {Routes} from "@angular/router";
import {UserLogin} from "./authentication/user.component";
import {UploadCenter} from "./uploadCenter/uploadCenter";
import {VendorInfo} from "./vendor/vendorInfo.component";
import {ProductInfo} from "./product/productInfo.component";
import {AuthGuard} from "./authentication/user.authguard";

export const rootRouterConfig: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'upload-center', component: UploadCenter, canActivate: [AuthGuard]},
  {path: 'vendor-info/:vendorId', component: VendorInfo, canActivate: [AuthGuard]},
  {path: 'product-info/:productId', component: ProductInfo, canActivate: [AuthGuard]},
  {path: 'login', component: UserLogin}
];
