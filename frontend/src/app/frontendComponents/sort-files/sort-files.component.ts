import {Component} from "@angular/core";

@Component
({
    selector: 'vendor-sort-files',
    templateUrl: './sort-files.component.html'
})

export class SortFilesComponent
{
    files;
    title: string = 'Dateien sortieren';
    description: string = 'Sortieren Sie die Dateien für Ihre gewünschte Darstellung'

}