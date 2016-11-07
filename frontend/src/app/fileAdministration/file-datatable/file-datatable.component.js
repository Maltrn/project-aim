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
/**
 * Created by Dustin Spallek on 07.11.2016.
 */
var core_1 = require('@angular/core');
var file_upload_service_1 = require('../fileAdministration-service/file-upload.service');
var FileDatatableComponent = (function () {
    function FileDatatableComponent(fileUploadService) {
        this.title = "File Datatable";
        this.files = fileUploadService.getFiles();
    }
    FileDatatableComponent = __decorate([
        core_1.Component({
            selector: 'filedatatable-view',
            templateUrl: 'app/fileAdministration/file-datatable/file-datatable.component.html',
            providers: [file_upload_service_1.FileUploadService],
        }), 
        __metadata('design:paramtypes', [file_upload_service_1.FileUploadService])
    ], FileDatatableComponent);
    return FileDatatableComponent;
}());
exports.FileDatatableComponent = FileDatatableComponent;
//# sourceMappingURL=file-datatable.component.js.map