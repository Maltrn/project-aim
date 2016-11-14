import {Routes} from "@angular/router";
import {UploadCenter} from "./uploadCenter/uploadCenter";
import {VendorInfoMain} from "./frontendComponents/main/vendor-info-main.component";
import {ProductInfoMain} from "./frontendComponents/main/product-info-main.component";


export const rootRouterConfig: Routes = [
    {path: '', redirectTo: 'upload-center', pathMatch: 'full'},
    {path: 'upload-center', component: UploadCenter},
    {path: 'anbieterinformationen', component: VendorInfoMain},
    {path: 'produktinformationen', component: ProductInfoMain},
];