

# OTM-harjoitustyö: Snake

## Dokumentaatio

[Vaatimusmaarittely](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Releaset

[Viikko 5](https://github.com/hallssus/omt-harjoitustyo/releases)

## Komentorivitoiminnot

### Testaus

Testaus suoritetaan komennolla

	mvn test

Testikattavuusraportti luodaan komennolla

	mvn jacoco:report

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto *target/site/jacoco/index.html*

### Suoritettavan jarin generointi

Komento 

	mvn package

generoi hakemistoon target suoritettavan jar-tiedoston *Snake-1.0-SNAPSHOT.jar*
