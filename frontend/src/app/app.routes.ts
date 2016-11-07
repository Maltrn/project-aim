import {Routes} from "@angular/router";
import {UploadFileComponent} from "./fileAdministration/file-upload/file-upload.component";
export const rootRouterConfig: Routes = [
    {path: '', redirectTo: 'upload-center', pathMatch: 'full'},
    {path: 'upload-center', component: UploadFileComponent},
];