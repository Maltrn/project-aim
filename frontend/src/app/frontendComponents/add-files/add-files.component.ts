/**
 * Created by Malte Scheller on 31.10.2016.
 */
import {Component} from "@angular/core";

@Component
({
    selector: 'add-files',
    templateUrl: './add-files.component.html'
})

export class AddFilesComponent
{
    title: string = 'Datei hinzufügen';
    description: string = 'Fügen Sie weitere Dateien hinzu'

}