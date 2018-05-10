# Käyttöohje
Lataa tiedosto [Snake-1.0-SNAPSHOT.jar](https://github.com/hallssus/omt-harjoitustyo/releases)

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla

	java -jar Snake-1.0.SNAPSHOT.jar

## Alkuvalikko

Sovellus käynnistyy näkymään, jossa ensin kysytään, haluaako pelaaja pelata yksin, vai kaverin kanssa. 

![Alkukysymys](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/kuvat/yksinvaiyhessa.png)

## Pelaajan tai pelaajien käyttäjätunnukset 

### Yksinpeli

![Ohjeet yksinpeliin]((https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/kuvat/yksinpelinimi.png)

Tähän kenttään pelaaja kirjoittaa haluamansa käyttäjätunnuksen. Yksinpelistä on kaksi versiota riippuen siitä, ruksittaako pelaaja Classic-painikkeen vai ei. Klassinen peli on normaali matopeli jossa mato kulkee kokoajan vakionopeudella. Ei-klassisessa pelissä mato nopeutuu jokaisen omenan myötä vähän, kunnes se saavuttaa maksiminopeutensa.

### Kaksinpeli

![Ohjeet kahdestaan](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/kuvat/ohjeetkahdestaan.png)

Näihin kenttiin pelaajat laittavat haluamansa käyttäjätunnukset näihin kenttiin. 

## Pelaaminen

### Yksinpeli

![Yksinpeli]((https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/kuvat/yksinpeli.png)

### Kaksinpeli

![Itse peli](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/kuvat/matopeli.png)

Kaksinpeliversio, jossa vihreät omenat ovat nopeusboosteja, ja punaiset oikeita omenoita.

## Tulokset

![Scoret](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/kuvat/scores.png)

Pelin jälkeen (tai sen aikana) voi painaa To scores-nappia, joka näyttää äskeisen pelin tulokset.

## Highscoret

![Highscoret](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/kuvat/highscores.png)

Viimeisenä näytetään kymmenen parasta pelaajaa pisteineen ruudussa.
