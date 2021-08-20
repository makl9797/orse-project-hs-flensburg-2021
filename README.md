# Orse - Order- and Servicemanager
Projektarbeit 2021 - Hochschule Flensburg
by Florian Schönknecht, Mats Klein, Zouhair Ijaad
## Beschreibung
Orse oder auch Order- and Servicemanager ist eine Anwendung für Unternehmen oder Personen die ihren Service digital unkompliziert verwalten wollen. Orse lässt sich auf die Wünsche
des Kunden anpassen, sodass nur die Features benötigt werden die der Anwender braucht.

Im aktuellen Status sind viele Features noch nicht vollständig, sodass die Beschreibung noch nicht zutrifft. 

## Anleitung zum Starten

Zum ausführen der Anwendung das Repository klonen oder als ZIP-Datei herunterladen.

Im Hauptverzeichnis folgenden Befehl ausführen:

```
> ./gradlew run 
``` 

Beim ersten Start müssen die Abhängigkeiten heruntergeladen werden, was etwas Zeit in Anspruch nehmen kann. 


Die Anwendung ist dann unter [localhost:8080](http://localhost:8080/) erreichbar

## Anleitung der Anwendung
### Arbeitsmodus
Die Anwendung startet im sogenannten "Arbeitsmodus".

Das heißt das Verändern oder entfernen von Modulen ist nicht möglich und es können nur die Module selbst genutzt werden.

Über die Knöpfe "Kunde hinzufügen" und "Objekt hinzufügen" können neue Kunden oder Objekte zur Datenbank hinzugefügt werden. Dies muss vor dem Buchen geschehen, 
da sonst keine Objekte zum Auswählen angezeigt werden.

Nach dem hinzufügen aktualisiert sich der Kalender. Wählt man einen Tag im Kalender werden die an diesem Tag verfügbaren Objekte angezeigt.
Man kann nun ein Objekt zur Buchung auswählen. 

Im Informationsfenster werden nun alle Informationen zum Objekt angezeigt. Dort kann nun auch der Kunde ausgewählt werden, der dieses Objekt buchen möchte. Vor dem Buchen müssen 
nun nur noch die Anzahl der Tage und der Preis für die Buchung festgelegt werden. 

Nachdem man auf Buchen geklickt hat aktualisiert sich der Kalender und das Objekt steht im gebuchten Zeitraum nicht mehr zur Verfügung.

Der Kalender zeigt an wenn nur noch weniger als 4 (Gelb) Objekte oder gar keine (rot) Objekte mehr verfügbar für den Tag sind.

### Bearbeitungsmodus
Im "Bearbeitungsmodus" lässt sich das Layout der Anwendung ändern. Die einzelnen Module können entfernt oder hinzugefügt werden. Die Größe und Position der Module wird hierbei
in einer vordefinierten Schrittgröße ermittelt.

In jeder Titelzeile eines Moduls gibt es ein Icon welches ein PopUp zur veränderung des Moduls dient. Über die äußeren Knöpfe lässt sich die Größe des Moduls ändern.
Über die inneren Knöpfe lässt sich die Position des Moduls ändern.

Über den Button "Module" lassen sich die verfügbaren Module beliebig oft hinzufügen. Zur Vereinfachung können Größe und Position schon vor dem hinzufügen geändert werden.

Der "Zurücksetzen"-Button setzt die Änderungen auf den Standpunkt zurück bei dem man in den Bearbeitungsmodus gewechselt ist.
