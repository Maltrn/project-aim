## 1. Login
### Vorbedingung
* Benutzer ist nicht angemeldet
* Benutzer hat einen gültigen Account
* Schnittstellen sind verfügbar

### Nachbedingung
* es findet eine Weiterleitung auf den geschützten Bereich statt

### Benutzer Aktion
* Benutzer gibt seine Anmeldedaten (Benutzername, Passwort) ein
* drückt auf Login/Enter

### System Aktion
* die Anmeldedaten werden vom Authentifizierungsserver validiert
* das System ruft Produkt- und Anbieterinformationen ab

### Fehlerfälle
* Anmeldedaten sind falsch / kein Konto vorhanden -> Meldung: Falsche Daten eingegeben
* Authentifizierungsservice nicht erreichbar -> Meldung: Technisches Problem, versuchen Sie es noch einmal (einloggen ist nicht möglich)
* Produktservice nicht erreichbar -> Meldung: Technisches Problem, versuchen Sie es noch einmal (einloggen ist nicht möglich)

### Erweiterung

### Abgrenzung
* Kein Passwort verändern
* Kein Passwort vergessen
* Kein Account anlegen
* Kein Benutzer merken

## 2. Logout
### Vorbedingung
* Benutzer ist angemeldet

### Nachbedingung
* es findet eine Weiterleitung auf eine vom Kunden gewünschte Seite statt

### Benutzer Aktion
* drückt auf Logout

### System Aktion
* Logout wird an den Authentifizierungsservice mit den Benutzerdaten geschickt

### Fehlerfälle
* Authentifizierungsservice nicht erreichbar -> Meldung: Technisches Problem, versuchen Sie es noch einmal (ausloggen ist nicht möglich)

### Erweiterung

### Abgrenzung

## 3. Benutzer bearbeitet Anbieter / Produkt Kurzbeschreibung
### Vorbedingung
* Benutzer ist angemeldet

### Nachbedingung
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)
* Daten werden in der Datenbank persistiert

### Benutzer Aktion
* Benutzer wählt links im Menü seinen zu bearbeitenden Punkt (Anbieter oder Produkt) aus
* Benutzer bearbeitet den Text der Kurzbeschreibung
* Benutzer klickt auf speichern / verwerfen (siehe Erweiterung)

### System Aktion
* System speichert die entsprechenden Daten in der Datenbank

### Fehlerfälle
* Eingaben sind zu lang (Felder sind begrenzt) -> Feld wird rot, Meldung: Zu lang!
* Eingaben sind ungültig (z.B.: HTML in Kurzbeschreibung) -> Feld wird rot, Meldung: Ungültige Eingabe!
* Felder sind leer -> Feld wird rot, Meldung: Bitte füllen Sie alle nötigen Felder aus

### Erweiterung
* Beim Verwerfen und Speichern wird die Maske nicht verlassen
* Beim Verwerfen werden alle ungespeicherten Daten verworfen
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)

### Abgrenzung
* Das Speichern der Daten kann nur durchgeführt werden, wenn der Benutzer auf Speichern klickt. Ungespeicherte Änderungen gehen verloren, wenn die Seite z.B. aktualisiert oder verlassen wird

## 4. Benutzer bearbeitet Anbieter / Produkt Detailbeschreibung
### Vorbedingung
* Benutzer ist angemeldet

### Nachbedingung
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)
* Daten werden in der Datenbank persistiert

### Benutzer Aktion
* Benutzer wählt links im Menü seinen zu bearbeitenden Punkt (Anbieter oder Produkt) aus
* Benutzer bearbeitet den Text der Detailbeschreibung
* Benutzer klickt auf speichern / verwerfen (siehe Erweiterung)

### System Aktion
* System speichert die entsprechenden Daten in der Datenbank

### Fehlerfälle
* Eingaben sind zu lang (Felder sind begrenzt) -> Feld wird rot, Meldung: Zu lang!
* Eingaben sind ungültig (z.B.: HTML in Kurzbeschreibung) -> Feld wird rot, Meldung: Ungültige Eingabe!
* Felder sind leer -> Feld wird rot, Meldung: Bitte füllen Sie alle nötigen Felder aus

### Erweiterung
* Beim Verwerfen und Speichern wird die Maske nicht verlassen
* Beim Verwerfen werden alle ungespeicherten Daten verworfen
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)

### Abgrenzung
* Das Speichern der Daten kann nur durchgeführt werden, wenn der Benutzer auf Speichern klickt. Ungespeicherte Änderungen gehen verloren, wenn die Seite z.B. aktualisiert oder verlassen wird

## 5. Benutzer bearbeitet Anbieter / Produkt Hauptbild
### Vorbedingung
* Benutzer ist angemeldet

### Nachbedingung
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)

### Benutzer Aktion
* Benutzer wählt links im Menü seinen zu bearbeitenden Punkt (Anbieter oder Produkt) aus
* Benutzer klickt auf Hauptbild auswählen
* Benutzer wählt aus der Liste von Dateien ein Hauptbild aus und bestätigt mit OK
* Benutzer klickt auf speichern / verwerfen (siehe Erweiterung)

### System Aktion
* System speichert die entsprechenden Auswahl in der Datenbank

### Fehlerfälle

### Erweiterung
* Beim Verwerfen und Speichern wird die Maske nicht verlassen
* Beim Verwerfen werden alle ungespeicherten Daten verworfen
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)

### Abgrenzung
* Das Speichern der Daten kann nur durchgeführt werden, wenn der Benutzer auf Speichern klickt. Ungespeicherte Änderungen gehen verloren, wenn die Seite z.B. aktualisiert oder verlassen wird
* Für die Hauptbild Auswahl stehen nur Bilddateien zur Verfügung (keine PDF)

## 6.1 Benutzer fügt Dateien zur Anbieter / Produkt Galerie hinzu
### Vorbedingung
* Benutzer ist angemeldet

### Nachbedingung
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)

### Benutzer Aktion
* Benutzer wählt links im Menü seinen zu bearbeitenden Punkt (Anbieter oder Produkt) aus
* Der Benutzer klickt auf Datei der Galerie hinzufügen
* Benutzer wählt aus der Liste von Dateien ein oder mehrere Dateien aus und bestätigt mit OK
* Benutzer klickt auf speichern / verwerfen (siehe Erweiterung)

### System Aktion
* System speichert die entsprechenden Verknüpfungen in der Datenbank

### Fehlerfälle

### Erweiterung
* Beim Verwerfen und Speichern wird die Maske nicht verlassen
* Beim Verwerfen werden alle ungespeicherten Daten verworfen
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)

### Abgrenzung
* Das Speichern der Daten kann nur durchgeführt werden, wenn der Benutzer auf Speichern klickt. Ungespeicherte Änderungen gehen verloren, wenn die Seite z.B. aktualisiert oder verlassen wird

## 6.2 Benutzer sortiert Daten in der Bilder/PDF Galerie
### Vorbedingung
* Benutzer ist angemeldet

### Nachbedingung
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)

### Benutzer Aktion
* Benutzer wählt links im Menü seinen zu bearbeitenden Punkt (Anbieter oder Produkt) aus
* Der Benutzer wählt einen zu verschiebenen Eintrag aus der Dateiliste aus
* Der Benutzer klickt über der Dateiliste auf den "Nach oben" oder "Nach unten" Button um das Element entsprechend zu verschieben
* Benutzer klickt auf speichern / verwerfen (siehe Erweiterung)

### System Aktion
* System speichert die entsprechenden Verknüpfungen in der Datenbank

### Fehlerfälle

### Erweiterung
* Beim Verwerfen und Speichern wird die Maske nicht verlassen
* Beim Verwerfen werden alle ungespeicherten Daten verworfen
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)

### Abgrenzung
* Das Speichern der Daten kann nur durchgeführt werden, wenn der Benutzer auf Speichern klickt. Ungespeicherte Änderungen gehen verloren, wenn die Seite z.B. aktualisiert oder verlassen wird

## 6.3 Benutzer entfernt Bilder/PDF aus einer Galerie (Anbieter / Produkt)
### Vorbedingung
* Benutzer ist angemeldet

### Nachbedingung
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)

### Benutzer Aktion
* Benutzer wählt links im Menü seinen zu bearbeitenden Punkt (Anbieter oder Produkt) aus
* Der Benutzer klickt in der Dateiliste neben dem zu entfernenden Eintrag auf den Entfernen Button
* Benutzer klickt auf speichern / verwerfen (siehe Erweiterung)

### System Aktion
* System speichert die entsprechende Verknüpfung in der Datenbank

### Fehlerfälle

### Erweiterung
* Entfernen meint in diesem Kontext nur das Aufheben der Verknüpfung zwischen Bild/PDF und Anbieter/Produkt (für Löschen siehe 'Dateien löschen / Produkt-/Kunden- Bilder/PDFs')
* Beim Verwerfen und Speichern wird die Maske nicht verlassen
* Beim Verwerfen werden alle ungespeicherten Daten verworfen
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)

### Abgrenzung
* Das Speichern der Daten kann nur durchgeführt werden, wenn der Benutzer auf Speichern klickt. Ungespeicherte Änderungen gehen verloren, wenn die Seite z.B. aktualisiert oder verlassen wird

## 7.1 Benutzer fügt einen Eintrag zu der Fakten-Tabelle hinzu
### Vorbedingung
* Benutzer ist angemeldet

### Nachbedingung
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)

### Benutzer Aktion
* Benutzer wählt links im Menü seinen zu bearbeitenden Punkt (Anbieter oder Produkt) aus
* Der Benutzer klickt unter der Tabelle auf das Plus um einen neuen Eintrag hinzuzufügen
* Der Benutzer kann in die neue Zeile einen neuen Eintrag eingeben
* Dieser Vorgang kann wiederholt werden
* Benutzer klickt auf speichern / verwerfen (siehe Erweiterung)

### System Aktion
* System speichert die entsprechenden Daten in der Datenbank

### Fehlerfälle
* Bei überschreiten der maximalen zulässigen Zeichenzahl wird die Eingabemaske rot eingefärbt

### Erweiterung
* Beim Verwerfen und Speichern wird die Maske nicht verlassen
* Beim Verwerfen werden alle ungespeicherten Daten verworfen
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)

### Abgrenzung
* Das Speichern der Daten kann nur durchgeführt werden, wenn der Benutzer auf Speichern klickt. Ungespeicherte Änderungen gehen verloren, wenn die Seite z.B. aktualisiert oder verlassen wird

## 7.2 Benutzer verschiebt einen Eintrag in der Fakten-Tabelle
### Vorbedingung
* Benutzer ist angemeldet

### Nachbedingung
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)

### Benutzer Aktion
* Benutzer wählt links im Menü seinen zu bearbeitenden Punkt (Anbieter oder Produkt) aus
* Der Benutzer klickt in der Tabelle auf den zu verschiebenen Eintrag
* Der Benutzer klickt über der Tabelle auf den "Nach oben" oder "Nach unten" Button um das Element entsprechend zu verschieben
* Benutzer klickt auf speichern / verwerfen (siehe Erweiterung)

### System Aktion
* System speichert die entsprechenden Daten in der Datenbank

### Fehlerfälle
* Der Benutzer versucht Elemente am oberen oder unteren Ende der Tabelle nach entsprechend oben oder unten zu verschieben -> Eingabe wird ignoriert

### Erweiterung
* Beim Verwerfen und Speichern wird die Maske nicht verlassen
* Beim Verwerfen werden alle ungespeicherten Daten verworfen
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)

### Abgrenzung
* Das Speichern der Daten kann nur durchgeführt werden, wenn der Benutzer auf Speichern klickt. Ungespeicherte Änderungen gehen verloren, wenn die Seite z.B. aktualisiert oder verlassen wird

## 7.3 Benutzer löscht einen Eintrag aus der Fakten-Tabelle
### Vorbedingung
* Benutzer ist angemeldet

### Nachbedingung
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)

### Benutzer Aktion
* Benutzer wählt links im Menü seinen zu bearbeitenden Punkt (Anbieter oder Produkt) aus
* Der Benutzer klickt in der Tabelle neben dem zu löschenden Eintrag auf den Löschen Button
* Benutzer klickt auf speichern / verwerfen (siehe Erweiterung)

### System Aktion
* System speichert die entsprechende Verknüpfung in der Datenbank

### Fehlerfälle

### Erweiterung
* Beim Verwerfen und Speichern wird die Maske nicht verlassen
* Beim Verwerfen werden alle ungespeicherten Daten verworfen
* Benutzer wird über seine durchgeführten Aktionen informiert (verwerfen, speichern)

### Abgrenzung
* Das Speichern der Daten kann nur durchgeführt werden, wenn der Benutzer auf Speichern klickt. Ungespeicherte Änderungen gehen verloren, wenn die Seite z.B. aktualisiert oder verlassen wird

## 8.1 Dateien hochladen / Produkt-/Kunden- Bilder/PDFs
### Vorbedingung
* Benutzer ist angemeldet
* Benutzer hat die Oberfläche für File-Upload ausgewählt

### Nachbedingung
* Benutzer wird über seine durchgeführten Aktionen informiert (Upload erfolgreich/fehlgeschlagen)
* vom Benutzer zum Hochladen ausgewählte Dateien wurden auf dem Server gespeichert 
* hochgeladene Dateien sind für Benutzer abrufbereit

### Benutzer Aktion
* Benutzer klickt auf Durchsuchen
* Benutzer wählt eine oder mehrere Dateien aus PopUp-Fenster und bestätigt die Auswahl

### System Aktion
* System prüft Dateien auf Gültigkeit (Größe, Typ)
* System speichert Daten in den für Kunden reservierten Datei-Ordner
* System gibt Meldung aus nachdem Daten erfolgreich auf Server gespeichert wurden

### Fehlerfälle
* reservierte Datenkapazität für Kunden ausgelastet --> Meldung: „Upload fehlgeschlagen…“
* Verbindung zum Server unterbrochen oder fehlgeschlagen --> Meldung: „Verbindungproblem…“

### Erweiterung

### Abgrenzung

## 8.2 Dateien ersetzen / Produkt-/Kunden- Bilder/PDFs
### Vorbedingung
* Benutzer ist angemeldet
* Benutzer hat die Oberfläche für File-Upload ausgewählt

### Nachbedingung
* Benutzer wird über seine durchgeführten Aktionen informiert (Upload erfolgreich/fehlgeschlagen)
* Die ausgewählte Datei wurde ersetzt
* die neuen Dateien wurden hochgeladen und auf dem Server gespeichert

### Benutzer Aktion
* Benutzer wählt die zu ersetzende Datei in der "Dateiübersicht" aus
* Benutzer klickt auf Ersetzen-Button
* Benutzer wählt neue Datei aus
* Benutzer bestätigt die Auswahl

### System Aktion
* System prüft Dateien auf Gültigkeit (Größe, Typ)
* System ersetzt die alte Datei durch die Neue
* System gibt Meldung aus nachdem Daten erfolgreich auf Server gespeichert wurden

### Fehlerfälle
* reservierte Datenkapazität für Kunden ausgelastet --> Meldung: „Upload fehlgeschlagen…“
* Verbindung zum Server unterbrochen oder fehlgeschlagen --> Meldung: „Verbindungprobleme…“
* Datenformat stimmt mit der zu ersetzenden Datei nicht überein

### Erweiterung

### Abgrenzung

## 8.3 Dateien löschen / Produkt-/Kunden- Bilder/PDFs

### Vorbedingung
* Benutzer ist angemeldet
* Benutzer hat die Oberfläche für Dateiverwaltung ausgewählt

### Nachbedingung
* Benutzer wird über seine durchgeführten Aktionen informiert (Upload erfolgreich/fehlgeschlagen)
* Die ausgewählten Dateien wurden gelöscht

### Benutzer Aktion
* Der Benutzer klickt in der Tabelle neben dem zu löschenden Eintrag auf den Löschen Button

### System Aktion
* System löscht die ausgewählte Datei
* System gibt Meldung aus nachdem Daten erfolgreich gelöscht wurde

### Fehlerfälle
* Verbindung zum Server unterbrochen oder fehlgeschlagen --> Meldung: „Verbindungproblem…“

### Erweiterung

### Abgrenzung
* Löschen ist endgültig, gelöschte Dateien können nicht wiederhergestellt werden