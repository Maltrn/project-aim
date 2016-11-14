"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require("@angular/core");
var VendorProfilePic = (function () {
    function VendorProfilePic(fileService) {
        this.fileService = fileService;
        this.title = 'Profil-Bild';
        this.pictureIds = [];
        this.selectedPicture = 'Noch keine Bild gewählt';
    }
    VendorProfilePic.prototype.ngOnInit = function () {
        this.getFileIDs();
    };
    VendorProfilePic.prototype.getFileIDs = function () {
        var _this = this;
        this.fileService.getPictureIdsOff().then(function (recIds) { return _this.sortID(recIds); });
    };
    VendorProfilePic.prototype.sortID = function (fileIds) {
        var accu = [];
        for (var _i = 0, fileIds_1 = fileIds; _i < fileIds_1.length; _i++) {
            var id = fileIds_1[_i];
            if (id.match(new RegExp('[a-zA-Z0-9].jpg')))
                accu.push(id);
        }
        if (accu.length > 0)
            this.pictureIds = accu;
        else
            this.pictureIds.push('Keine Bilder gefunden.');
    };
    VendorProfilePic.prototype.onSelect = function (id) {
        alert(id);
        this.selectedPicture = id;
    };
    VendorProfilePic = __decorate([
        core_1.Component({
            selector: 'vendor-info-profile-pic',
            templateUrl: './profile-pic.component.html',
            styleUrls: []
        })
    ], VendorProfilePic);
    return VendorProfilePic;
}());
exports.VendorProfilePic = VendorProfilePic;
