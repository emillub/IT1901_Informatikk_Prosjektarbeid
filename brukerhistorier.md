# Brukerhistorier
Her skriver vi brukerhistorier. Brukerhistoriene brukes for å beskrive hvilke behov applikasjonen vår dekker, og hva som kreves for å dekke dem. Når vi oppretter issues i gitlab, knytter vi dem opp mot brukerhistorien vi jobber under ved hjelp av labels.

## Konvensjon for brukerhistorier: 
### Tittel: Funksjon (BH nummer)
Historie: "Som en (Hvem/Rolle) ønsker jeg...(Hva/Mål) slik at...(Hvorfor/Nytteverdi)"
#### Hva som må til:
* Funksjon 1
* Funksjon 2


### Grensesnitt (BH 1)
Som bruker ønsker jeg å ha en oversikt over bøker og muligheten til å se vurderinger av dem, slik at jeg kan bestemme meg for om de er verdt å lese.
### Hva må til
* GUI-side for display av bøker
* GUI-side for display av vurderinger per bok

### Vurderinger (BH 2)
Som bruker ønsker jeg å legge igjen en signert vurdering fra 1-5 av bøker jeg har lest, så andre kan vite om de er verdt å lese. 
#### Hva må til: 
*   Bruker må ha mulighet til å skrive inn navnet sitt i appen
*   BookReview-klasse må ta inn et navn-parameter
*   Bruker-klasse må ha mulighet for å opprette en review
*   Bruker må ha mulighet til å velge en bok og vurdere den 