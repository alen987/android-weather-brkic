

Weather app
==================================
1. Prikaz trenutne prognoze za mjesto u kojem se korisnik nalazi
2. Pretraga željenog grada te pregled današnje prognoze za taj grad
3. Pregled prognoze za najmanje tri sljedeća dana za pretraženi grad
4. Ako korisnik dopusti, aplikacija mora u pozadini svakih pola sata provjeravati vremensku
   prognozu za trenutnu lokaciju te prikazati sistemsku notifikaciju u slučaju lošeg vremena u
   iduća 2 sata (kiša, olujno nevrijeme i slično).
5. Dodati u aplikaciju neki oblik cacheinga za offline support i brže inicijalno
   dohvaćanje podataka i populiranje UI-a.

Navigaciju među screenovima složi po želji.
Bitno je misliti i na UX aplikacije; ako je mrežna veza nedostupna ili grad nije nađen i slično,
prikazati odgovarajuće error poruke (u dialogu, in-app widgetu ili slično).

Konfiguracija Android projekta
------------
● Minimalna verzija Androida podržana mora biti 23 (minSdkVersion 23)
● Target verzija Android OS-a neka bude minimalno 31 (targetSdkVersion 31)
● Ime aplikacije neka sadržava i tvoje prezime (npr. “Weather Prezime”)
● Ostatak konfiguracije postavi proizvoljno