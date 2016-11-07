"use strict";
var FileUploadService = (function () {
    function FileUploadService() {
        this.dateien = [{ name: "tolleDatei.pdf", date: "01.09.2015", size: 4 },
            { name: "hundebabies.jpg", date: "12.12.2016", size: 12 },
            { name: "witzigerLurch.gif", date: "23.08.2016", size: 48 },
            { name: "Bauplan.pdf", date: "09.09.2015", size: 128 }];
    }
    FileUploadService.prototype.getFiles = function () {
        return this.dateien;
    };
    FileUploadService.prototype.fillFilesFromBackend = function () {
    };
    return FileUploadService;
}());
exports.FileUploadService = FileUploadService;
//# sourceMappingURL=file-upload.service.js.map