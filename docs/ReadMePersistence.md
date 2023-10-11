# README - Persistence

## Introduksjon
* Hensikten til Persistence modulen er å håndtere lagring av brukerdata.
* Data lagres i "Library.json" som ligger i "bookapp/persistence/src/main/resources"-mappen.

## Teknologier brukt
* Jackson bibloteket
	* Jackson-bibloteket konverterer objekter fra core-modulen om til JSON format som skrives til fil.

## Funksjonalitet
* Modulens funksjonalitet er å skrive til fil og å lese fra fil. Dette gjøres hovedsakelig gjennom funksjonene "writeBooksToFile" og "readBooksFromFile".
* ```writeBooksToFile()```
	* Denne funksjonen tar inn en liste Book-objekter. En instans av ObjectMapper går deretter inn i de ulike Book-instansene, henter ut relevant informasjon og skriver det til fil. 
*  ```readBooksFromFile()```
	* Denne funksjonen tar ikke inn noen parametere fordi informasjonen den trenger ligger i "Library.json". Funksjonen går inn i denne filen og oppretter instanser av klassene fra core basert på informasjonen i json filen.


## Oversikt - Releases
1. Release 2 - Skriving til fil gjennom Jackson biblotek
	* Filformat
	* Implisitt lagring eller Dokumentmetafor


## Release 2
### 1. Definere/dokumentere filformat:

  

#### Filformat:

-   Dataen lagres på JSON format. JSON er fleksibelt og passer godt for objekt-strukturerte data.  
    
-   Brukerdata (Review og User) skrives til fil hver gang «Vurder» knappen brukes. Dataen lagres i Book-objekter som skrives til ""Library.json"".

  

#### Eksempel:

```

{ "books": [ { "title": "Eksempelbok 1", "reviews": [ { "user": "Brukernavn 1", "rating": 4 }, { "user": "Brukernavn 2", "rating": 3 } ] }, ... ] }

```

  

  

#### Forklaring:

  

- Her har vi en hovednøkkel "books" som inneholder en liste over bøker. Hver bok har en "title" og en liste over "reviews».

- Hver "review" inneholder en "user" og en "rating».

  

  

### 2. Valg og refleksjon mellom Implisitt lagring og Dokumentmetafor

  

  

#### Dokumentmetafor (desktop):

##### Fordeler:

- Brukere er ofte kjent med denne metaforen fordi den etterligner kjente fysiske objekter og miljøer, som skrivebord og dokumenter.

- Gir en tydelig visualisering av data og handlinger.

  

##### Ulemper:

- Kan noen ganger være mindre effektivt for enkle applikasjoner.

- Tar mer skjermareal.

  

#### Implisitt lagring (app):

##### Fordeler:

- Gir en rask og sømløs brukeropplevelse. Brukerne trenger ikke bekymre seg for manuell lagring.

- Reduserer muligheten for brukerfeil, som å glemme å lagre.

  

##### Ulemper:

- Brukere kan være usikre på om dataen faktisk er lagret.

- Kan føre til utilsiktet overlagring av ønsket data.

  

  

#### Konklusjon for din applikasjon:

  

-   Applikasjonen bruker dokumentmetafor knyttet til lagring av brukerdata:  
    

  

1.  Manuell Lagring: Bruk av "vurder"-knappen for å lagre en vurdering er lik praksisen med å manuelt lagre et dokument. Dette gir brukeren full kontroll over når dataene skal lagres, og dette gir en klar indikasjon på handlingen som blir utført.  
    
2.  Visualisering av Objekter: Ved å presentere bøker i en liste og tillate brukerne å velge en bestemt bok før de gir en vurdering, etterligner applikasjonen handlingen med å velge et fysisk dokument (i dette tilfellet en bok) før man gir en vurdering.  
    
3.  Sekvensiell Interaksjon: Brukerne må først velge en bok, deretter legge inn en vurdering, og til slutt bekrefte vurderingen ved å trykke på "vurder". Denne trinnvise prosessen minner om den sekvensielle naturen ved å håndtere fysiske dokumenter.