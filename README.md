

# OTM-harjoitustyö: Snake

Sovellus on perinteinen matopeli, jota voi halutessaan pelata joko yksin nuolinäppäimillä tai WASDilla, tai kaverin kanssa, jolloin toinen on nuolinäppäimet ja toinen WASD.

Alussa pelaajien nimet kysytään, pelaajat pelaavat pelin ja kuoleman jälkeen päästään katsomaan pisteitä klikkaamalla "To scores" näppäintä. Pisteet lasketaan kertomalla madon pituus kuluneella ajalla, paitsi jos on se mato joka on hävinnyt, miinustetaan tältä 10 pistettä. Tämä pistelaskutyyli on osoittautunut huonoksi, sillä pisteitä saa liian helposti vain hengailemalla pelissä tarpeeksi kauan, joten se täytyy tulevaisuudessa korjata. Pelaajan nimi ja tulos kirjoitetaan tietokantaan ja viimeinen ikkuna näyttää top-10 listan pelaajista. Jokin kiva erikoisominaisuus madoille olisi kiva tehdä vielä, kuten esim. toisen madon ampumismahdollisuus. Myöhemmillä viikoilla sitten. 

## Dokumentaatio

[Vaatimusmaarittely](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

## Releaset

[Viikko 5](https://github.com/hallssus/omt-harjoitustyo/releases)
[Viikko 6](https://github.com/hallssus/omt-harjoitustyo/releases/tag/Viikko6)

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

### Checkstyle

Tiedostoon [Checkstyle.xml](https://github.com/hallssus/omt-harjoitustyo/blob/master/Snake/checkstyle.xml) määrittelevät tarkistukset suoritetaan komennolla

	mvn jxr:jxr checkstyle:checkstyle

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto *target/site/checkstyle.html*

### JavaDoc

JavaDoc generoidaan komennolla 

	mvn javadoc:javadoc

JavaDocia voi tarkastella avaamalla selaimella tiedosto *target/site/apidocs/index.html*
