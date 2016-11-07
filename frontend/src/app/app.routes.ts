import {Routes} from "@angular/router";
import {UploadCenter} from "./uploadCenter/uploadCenter";

export const rootRouterConfig: Routes = [
    {path: '', redirectTo: 'upload-center', pathMatch: 'full'},
    {path: 'upload-center', component: UploadCenter},
];