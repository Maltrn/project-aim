import {Component} from "@angular/core";

@Component
({
    selector: 'vendor-notes',
    templateUrl: './facts.component.html'
})

export class FactsTableComponent
{
    title: string = 'Faktentabelle';
    description: string = 'Zur Bearbeitung der Inhalte in der Faktentabelle'
}