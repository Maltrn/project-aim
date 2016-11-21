import {Injectable} from "@angular/core"

@Injectable()
export class ConfigImporter {

    private fileLocation:String = "src/main/resources/config.properties"

    private fs = require('fs');

    constructor() {
        this.showFile();
    }

    showFile() {

        this.fs.readFile(this.fileLocation, function (err, data) {
            if (err) {
                return console.error(err);
            }
            console.log("Asynchronous read: " + data.toString());
        });
    }
    // readThis() : void {
    //     var file:File = new File(this.fileLocation);
    //     var myReader:FileReader = new FileReader();
    //
    //     myReader.onloadend = function (e) {
    //         // you can perform an action with readed data here
    //         console.log(myReader.result);
    //     }
    //
    //     myReader.readAsText(file);
    // }
}