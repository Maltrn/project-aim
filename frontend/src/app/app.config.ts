import {Injectable} from "@angular/core";
import fs = module("fs");

@Injectable()
export class ConfigImporter {

    private fileLocation: String = "src/main/resources/config.properties";

    constructor() {
        this.showFile();
    }

    showFile() {
        fs.readFile(this.fileLocation, function (err, data) {
            if (err) {
                return console.error(err);
            }
            console.log("Asynchronous read: " + data.toString());
        });
    }
}