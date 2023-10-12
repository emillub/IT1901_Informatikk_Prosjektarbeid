# README - Persistence

## Introduksjon
* Fxui-modulen står for det visuelle grensesnittet til applikasjonen og benytter både Core- og persistance-modulene
* "Mainwindow.fxml" som ligger i "bookapp/fxui/src/main/resources"-mappen åpnes og leses ved hjelp av JavaFx, og kontrolleres ved hjelp av AppController-klassen

## Teknologier brukt
* JavaFX
	* JavaFX er del av Java Development Kit, og er et bibliotek som lar deg opprette grafiske grensesnitt for både desktop og mobil med java
* Scene Builder
  * Scene Buider er applikasjon som gjør det lettere å lage det grafiske grensesnittet til en javafx-app. Det er brukt til å skrive "Mainwindow.fxml", men kreves ikke for å kjøre Bookapp.

## Funksjonalitet
* Modulen kjøres fra BookApp-klassen som leser og åpner "Mainwindow.fxml" som et grafisk grensesnitt.
  * ```start(Stage stage)```
    * Finner "Mainwindow.fxml" i resources-mappen og åpner det med javafx
* AppController-klassen er knyttet til Mainwindow.fxml og er ansvarlig for all funksjonalitet i brukergrensesnittet. Den inneholder referanser til alle objektetene Det meste av logikken ligger i Core-modulen, men kontrolleren knytter bruker-input til funksjoner i core- og persistance-modulene og passer på at det grafiske gejnspeiler det som skjer i kjernelogikken.

## Skjermbilder
![Viser innloggingsside](/docs/Bilder/Innlogging.png)
![Viser hovedside før review](/docs/Bilder/PreReviewR2.png)
![Viser hovedside etter review](/docs/Bilder/PostReviewR2.png)