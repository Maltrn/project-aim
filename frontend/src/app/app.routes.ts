import {Routes} from "@angular/router";
import {UserLogin} from "./authentification/user.component";
import {UploadCenter} from "./uploadCenter/uploadCenter";
import {VendorInfoMain} from "./informationComponents/main/vendor-info-main.component";
import {ProductInfoMain} from "./informationComponents/main/product-info-main.component";

export const rootRouterConfig: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'upload-center', component: UploadCenter},
  {path: 'anbieterinformationen', component: VendorInfoMain},
  {path: 'produktinformationen', component: ProductInfoMain},
  {path: 'login', component: UserLogin}
];

