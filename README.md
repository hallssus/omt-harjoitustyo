

# OTM-harjoitustyö: Snake

Sovellus on perinteinen matopeli, jota voi halutessaan pelata joko yksin nuolinäppäimillä tai WASDilla, tai kaverin kanssa, jolloin toinen on nuolinäppäimet ja toinen WASD. Alussa pelaajien nimet kysytään, pelaajat pelaavat pelin ja kuoleman jälkeen päästään katsomaan pisteitä klikkaamalla "To scores" näppäintä. Pisteet lasketaan kertomalla madon pituus kuluneella ajalla, paitsi jos on se mato joka on hävinnyt, miinustetaan tältä 10 pistettä. Pistelaskua täytyy vielä miettiä myöhemmässä vaiheessa. Lisäksi tavoitteenani on käyttää databasea listaamaan pelin high scoret. Jokin kiva erikoisominaisuus madoille olisi myös kiva, kuten esim. toisen madon ampumismahdollisuus. Myöhemmillä viikoilla sitten. 

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

### Checkstyle

Tiedostoon [Checkstyle.xml](https://github.com/hallssus/omt-harjoitustyo/blob/master/Snake/checkstyle.xml) määrittelevät tarkistukset suoritetaan komennolla

	mvn jxr:jxr checkstyle:checkstyle

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto *target/site/checkstyle.html*
