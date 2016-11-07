"use strict";
var FileUploadService = (function () {
    function FileUploadService() {
    }
    FileUploadService.prototype.getFileNames = function () {
        return ["File1", "File2", "File3"];
    };
    FileUploadService.prototype.getUploadDate = function () {
        return ["01.10.2016", "23.09.2016", "02.09.2016"];
    };
    FileUploadService.prototype.getFileSize = function () {
        return ["2.0 mb", "1.51 mb", "812 kb"];
    };
    return FileUploadService;
}());
exports.FileUploadService = FileUploadService;
//# sourceMappingURL=file-upload.service.js.map