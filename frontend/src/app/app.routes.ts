import {Routes} from "@angular/router";
import {UserLogin} from "./authentification/user.component";

export const rootRouterConfig: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  //{path: 'upload-center', component: UploadCenter},
  //{path: 'anbieterinformationen', component: VendorInfoMain},
  //{path: 'produktinformationen', component: ProductInfoMain},
  {path: 'login', component: UserLogin}
];

