# Release 2 for IT1901 - Gruppe 49

## Innholdsfortegnelse

1. Introduksjon
2. Struktur og funksjonalitet
3. Refleksjoner
4. Oppsummering

### 1. Introduksjon

Release 2 introduserer en rekke endringer til prosjektet sammenlignet med Release 1. Vi har endret prosjektet til å være strukturert på en mer optimal måte med bedre testningsgrader på et grundigere nivå. Vi har introdusert nye tester og metoder for å feilsøke koden i tillegg til et jackson-fillagringssystem. Gjennom erfaring og interne diskusjoner har vi kommet fram til konvensjoer og koderutiner som har virket effektivt. Vi har utnyttet gitlabs funksjonalitet ytterligere og har kodet sammen i par. Denne release-filen skal ta for seg de overordnede endringene og refleksjoner rundt arbeidet gjort. For mer spesifikk informasjon rundt kjøring og konfigurasjoner, vennligst se [readme.md](../readme.md)

### 2. Struktur og funksjonalitet

Prosjektet til release 1 var en en-moduls konfigurasjon hvor filhåntering, GUI og domenelogikk eksisterte om hverandre. Vi hadde en pom.xml fil som håndterte alle konfigurasjoner. For release 2 har vi introdusert en 3-moduls struktur. Vi har nå skilt domenelogikk, GUI og filhåndtering i tre forskjellige moduler som snakker sammen; core, fxui og persistens. En overordnet pom.xml-fil definerer "regler" for hele prosjektet mens mer spesifikke konfigurasjoner eksisterer innen hver moduls pom.xml-fil.

Vi har lagt til ekstra funksjonalitet som nå tvinger brukere til å oppgi et navn hvis de ønsker å vurdere en bok i tillegg til mer sofistikert filhåndtering gjennom et jackson-biblotek med et json format. Dette gjør at vi kan lagre input fra programmet på en mer effektiv måte. For mer detaljert informasjon rundt dette, vennligst se [Readme-persistens](../persistence/readme.md)

Prosjektet er også konfigurert for Eclipse Che.

#### Brukerhistorer
Vi har også inkludert flere brukerhistorier for å videre beskrive formålet med applikasjonen og for å illustrere funksjonalitet. Disse finnes i [brukerhistorier](docs\brukerhistorier.md). For release 2 er dette BH2 og BH3.
  
### 3. Refleksjoner

Friheten rundt valgene vi har tatt for denne releasen ligger i all hovedsak rundt arbeidsvaner og filhåndteringssystemer. For å ta det enkleste først har vi valgt en dokumentmetafor-lagringsmetode. Dette handler i stor grad om at det gjør det tydelig for brukere når inputen deres er lagret. Mer om dette i [Readme-persistens](../persistence/readme.md). 

Gjennom arbeidet gjort har vi funnet en struktur hvor vi baserer oss på scrum-teknikken med litt modifiseringer. Vi startet med ukentlige møter, hvor vi har jobbet med individuelle oppgaver mellom møtene, men mot senere i innleveringen ble det behov for mer samarbeid på problemene og vi tok i bruk par-programmering. Ettersom prosjektet er konfigurert til å fungere på våre individuelle datamaskiner, har vi ikke utnyttet Eclipse Che gjennom "cloud"-utvikling. Skulle vi ønske det, er prosjektet satt opp til å fungere i Eclipse Che.

### 4. Oppsummering

Release 2 introduserer altså ny struktur i prosjektet med utvidet funksjonalitet. Det finnes nå beskrivelser av modulene i deres respektive readme-filer. Vi har nå et fungerende "rating"-system som lar brukere interagere med applikasjonen og man kan se gjennomsnittsvurderinger av bøkene i applikasjonen.

Effektiviteten i gruppa har vært god og vi har tilpasset oss gjennom å endre arbeidsmetoder hvor det har vært behov for dette. Vi har fulgt konvensjoner avtalt i fellesskap ved bruk av gitlab. Derfor skal navigering av prosjektet være enkelt.