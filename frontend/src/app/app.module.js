/**
 * Created by Maltron on 31.10.2016.
 */
"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var platform_browser_1 = require('@angular/platform-browser');
var forms_1 = require("@angular/forms");
var app_component_1 = require("./appView/app.component");
var add_files_component_1 = require("./vendorInformation/add-files/add-files.component");
var vendor_detailed_discription_component_1 = require("./vendorInformation/detailed-description/vendor-detailed-discription.component");
var vendor_notes_1 = require("./vendorInformation/notes/vendor-notes");
var vendor_info_main_component_1 = require("./vendorInformation/main/vendor-info-main.component");
var vendor_short_discription_component_1 = require("./vendorInformation/short-description/vendor-short-discription.component");
var vendor_profile_pic_component_1 = require("./vendorInformation/profile-pic/vendor-profile-pic.component");
var vendor_sort_files_component_1 = require("./vendorInformation/sort-files/vendor-sort-files.component");
var file_service_1 = require("./vendorInformation/profile-pic/file.service");
var http_1 = require("@angular/http");
var file_upload_component_1 = require("./fileAdministration/file-upload/file-upload.component");
var file_datatable_component_1 = require("./fileAdministration/file-datatable/file-datatable.component");
// Imports for loading & configuring the in-memory web api
var angular_in_memory_web_api_1 = require('angular-in-memory-web-api');
var in_memory_data_service_1 = require('./vendorInformation/backend-semu/in-memory-data.service');
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            imports: [platform_browser_1.BrowserModule,
                forms_1.FormsModule,
                http_1.HttpModule,
                angular_in_memory_web_api_1.InMemoryWebApiModule.forRoot(in_memory_data_service_1.InMemoryDataService)],
            declarations: [app_component_1.AppComponent,
                vendor_info_main_component_1.VendorInfoMain,
                add_files_component_1.VendorAddFiles,
                vendor_detailed_discription_component_1.VendorDetailedDiscription,
                vendor_short_discription_component_1.VendorShortDiscription,
                vendor_notes_1.VendorNotes,
                vendor_profile_pic_component_1.VendorProfilePic,
                vendor_sort_files_component_1.VendorSortFiles,
                file_upload_component_1.UploadFileComponent,
                file_datatable_component_1.FileDatatableComponent,
            ],
            bootstrap: [app_component_1.AppComponent],
            providers: [file_service_1.FileService]
        }), 
        __metadata('design:paramtypes', [])
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map