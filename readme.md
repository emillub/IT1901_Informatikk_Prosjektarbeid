# Bokvurderings - prosjekt
 
Prosjektet er ment for å være en bok vurderings applikasjon. 

Som en bruker er du ment til å skrive inn navnet ditt slik at du kan få tilgang til funksjonene i appen. Funksjonene i appen inkluderer en side hvor du kan se gjennom bøker og vurderinger fra andre brukere om disse bøkene. Du kan også legge til en vurdering selv for en bok. Vurderingssytemet for en bok fungerer slik at du vurderer boken på en skala fra 1 til 5, hvor 1 og 5 hhv. forteller at du likte boken dårlig eller godt. Funksjonene til appen er ment til å være enkle og vårt hovedfokus ligger i kvalitet, effektivt samarbeid og gitlab synkronisasjoner. 

# Bygging/kjøring av applikasjon

Applikasjonen bruker maven til bygging og kjøring. 

For å bygge prosjektet, naviger til rotprosjekt-mappen og kjør mvn install i terminalen. 

For å kjøre prosjektet, naviger til gr2349 modulen ved hjelp av cd-kommandoer. Kjør dermed mvn javafx:run. Viktig at mvn install er kjørt før mvn javafx:run for at prosjektet skal kjøre. 

# Krav til Java- og Maven versjon

Tidligste java versjon som fungerer til å kjøre appen er java versjon 16.0.1.
Maven versjon som kreves for å kjøre appen er 3.9.4.

# Skjermbilder for funksjonalitet til innlevering 1

To skjermbilder som viser funksjonalitet knyttet til innlevering 1:

![Viser innloggingsside](/docs/Bilder/Innlogging.png)
![Viser hovedside](/docs/Bilder/Hovedside.png)

# Arbeidsvaner, arbeidsflyt og kodekvalitet

Dette avsnittet gir en oversikt over viktige valg og tilnærminger som har blitt gjort i løpet av prosjektets utvikling relatert til arbeidsvaner, arbeidsflyt og kodekvalitet i henhold til Release 2. 

## Arbeidsvaner

__Agil utvikling__ - Vi følger en agil utviklingsmetodikk gjennom prosjektet. Dette tillater oss å tilpasse oss endringer og levere jevnlige oppdateringer. Denne metodikken inkluderer regelmessige sprinter for å forbedre prosessen. 


__Kommunikasjon__ - Gruppen benytter seg av regelmessige og faste møter i utviklingen. For å opprettholde god kvalitet og lav redundans er medlemmene i gruppen også forstått med at mer akutte møter kan forekomme. Videre bruker gruppen "Facebook Messenger" som hovedkanal for kommunikasjon mellom de faste møtene. Dette gir oss muligheten til å kommunisere i enkle trekk og samtidig avtale møter og arbeidstimer mer hyppig da prosjektet kan by på forskjellige utfordringer som krever møter utenfor de faste. 

## Arbeidsflyt

__GitLab__ - Vi bruker GitLab for versjonskontroll, og alle endringer blir gjort i egne branches. Vi utnytter funksjonene til pull og push for å samle endringene i master branchen. Dette gir en oversiktlig tilnærming til kodeendringer og muliggjør nødvendige kodeanmeldelser. 

__Oppgavestyring__ - I GitLab bruker vi "Issues" og "Tasks" for oppgavestrying. Disse blir opprettet til et "Label" og en "Milestone". Labelene angir hvilken del av prosjektet issuet tilhører. For eksempel, opprettes det et issue angående Readme.md - arbeid blir dette knyttet til labelet "Dokumentasjon/Prosjektstruktur". Milestone´s angir til hvilken del av prosjektet issuet jobbes mot. For eksempel, innlevering 1, 2, 3 eller 4. På møter avtales hvilke arbeidsoppgaver hvert medlem skal gjennomføre til neste gang og issues (med tilhørende informasjon) opprettes på disse møtene. I et resultat, gir dette oss en oversiktlig fremdrift og vi kan enkelt gå tilbake i prosjektet hvis det skulle vært nødvendig. Dette støtter den agile utviklingsmetodikken vår. 

## Kodekvalitet

__Testing__ - Vi har vedtatt en tilnærming der hovedfunksjonene i appen blir testet. 

__Testdekningsgrad__ - Gruppen bruker Jacoco som middel for testdekningsgrad av kode. 

## Arkitektur av app med PlantUML

![Arkitektur](https://www.planttext.com/)
















