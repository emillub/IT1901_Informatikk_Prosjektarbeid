# README - Persistence

## Introduksjon
* Hensikten til Core modulen er å håndtere kjernelogikken i appen.

## Teknologier brukt
* 

## Funksjonalitet
* Book-klassen holder informasjon om en bok og referanser til BookReview-objekter. Den representerer en bok med vurderinger og har en enkel ```toString()```-funksjon som brukes i grensesnittet.
  * ```getAvarageRating()```
    * Itererer over BookReview-objektene og regner ut gjennomsnittet av alle rating-ene
  * ```validateReview(BookReview review)```
    * Sjekker at en review ikke finnes fra før, og om en bruker allerede har skrevet en review av boka. Gir i så fall ```IllegalArgumentException```
* BookReview-klassen representerer en vurdering av en bok. Den har refereanser til User-objektet som opprettet det og Book-objektet det tilhører. Den har også en statisk int-array som bestemmer verdiene man kan gi som rating.
  * ```BookReview(Book book, User reviewer, int rating)```
    * Oppretter et BookReview-objekt. Sjekker at den blir opprettet gjennom en bruker og gir error hvis ikke.
  * ```Validrating(int r)```
    * Sjekker om ratingen som er gitt er lovling og gir error om ikke
* User-klassen håndterer alt som har med bruker å gjøre. Den inneholder lite logikk, men holder informasjon om brukereren, og det er fra den BookReview-objekter opprettes.
  * ```writeReview(Book bok, in rating)```
	* Oppretter et BookReview-objekt med en rating og knytter det til en bok.