# projecttwomicroservice

[![Build Status](https://travis-ci.org/meandor/projecttwomicroservice.svg?branch=master)](https://travis-ci.org/meandor/projecttwomicroservice)

This microservice can add and maintain additional data to pbx provider and their products.

It is build on the [Edison Microservice](https://github.com/otto-de/edison-microservice) and uses AngularJS.

## Usage
This project uses gradle to build everything. There is a custom script to start the server, do all the testing and so on.

To run the microservice simply use the go script: ````./bin/go bootRun````.

To test everything run: ````./bin/go test````

## Experts
* frontend (Dustin Malte)
* backend (daniel, jana, rene, christof)
* api ()

## Fragenkatalog
* Was bekommen wir vom anderen System
* wie soll gui aussehen
* was für daten sollen für wen gespeichert werden
* was für formate für produktbroschüren und downloads
* wo finden wir schnittstellenbeschreibungen
* welche funktionen sollen abgedeckt werden

## Antworten

* teaser und name sollen in der länge begrenzt sein
* Mock daten für Schnittstellen -> User, Produkte
* Texte sollen anpassbar sein im Aussehen -> Detail texte
* Copy Paste word kompatibel
* PDF, Bilder hochladen können (jpeg png gif)
* anbieter name
* anbieter beschreibung
* anbieter detail beschreibung
* Anbieter texte (beliebig viele)
* anbieter bilder (beliebig viele)
* anbieter tabelle mit merkmal links, rechts beschreibung warum so toll
* anbieter schlagworte mit erklärung (beliebig viele)
* tabellarische erfassung mit schlagworten
* Markierung von einem Hauptbild
* Produkt name
* Produkt detail beschreibung
* Produkt texte (beliebig viele)
* Produkt bilder (beliebig viele) gallerie
* Produkt tabelle mit merkmal links, rechts beschreibung warum so toll
* Produkt schlagworte mit erklärung (beliebig viele)
* tabellarische erfassung mit schlagworten
* Markierung von einem Hauptbild

## Tools
* [Slack](https://projmicro.slack.com)
* [Waffle.io](https://waffle.io/)
* IntelliJ