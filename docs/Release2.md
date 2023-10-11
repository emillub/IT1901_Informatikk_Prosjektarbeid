# Release 2 for IT1901 - Gruppe 49

## Innholdsfortegnelse

1. Introduksjon
2. Struktur og funksjonalitet
3. Refleksjoner
4. Oppsummering

### 1. Introduksjon
Release 2 introduserer en rekke endringer til prosjektet sammenlignet med Release 1. Vi har endret prosjektet til å være strukturert på en mer optimal måte med bedre testningsgrader på et grundigere nivå. Vi har introdusert nye tester og metoder for å feilsøke koden i tillegg til et jackson-fillagringssystem. Gjennom erfaring og interne diskusjoner har vi kommet fram til konvensjoer og koderutiner som har virket effektivt. Vi har utnyttet gitlabs funksjonalitet ytterligere og har kodet sammen i par. Denne release-filen skal ta for seg de overordnede endringene og refleksjoner rundt arbeidet gjort. For mer spesifikk informasjon rundt kjøring og konfigurasjoner, vennligst se [README-parent](../readme.md)

### 2. Struktur og funksjonalitet
Prosjektet til release 1 var en en-moduls konfigurasjon hvor filhåntering, GUI og domenelogikk eksisterte om hverandre. Vi hadde en .xml fil som hånterte alle konfigurasjoner. For release 2 har vi introdusert en 3-moduls struktur. Vi har nå skilt domenelogikk, GUI og filhåndtering i tre forskjellige moduler som snakker sammen; core, fxui og persistens. En overordnet pom.xml-fil som definerer "regler" for hele prosjektet mens mer spesifikke konfigurasjoner eksisterer innen hver moduls pom.xml-fil.

Vi har lagt til ekstra funksjonalitet som nå tvinger brukere til å oppgi et navn hvis de ønsker å vurdere en bok, i tillegg til mer sofistikert filhåndtering gjennom et jackson-biblotek med et json format. Dette gjør at vi kan lagre inputen programmet får fra brukerne på en mer effektiv måte. For mer detaljert informasjon rundt dette, vennligst se [Readme-persistens] (LEGGTILLINKSENERE)

Prosjektet er også konfigurert til Eclipse Che. 

### 3. Refleksjoner
Hvilke valg har vi tatt og hvorfor har vi gjort dem?

### 4. Oppsummering

HUSK Å SKRIVE RELEASE OPP MOT TILBAKEMELDINGENE GITT PÅ RELEASE 1