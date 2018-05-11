# Vaatimusmäärittely

## Sovelluksen tarkoitus
Sovellus on jo kaikille tuttu Matopeli pienellä tvistillä. Peliä voi pelata joko yksin, jolloin se toimii normaalin matopelin tapaa WASDilla, tai kaksin kaveria vastaan samalla koneella, jolloin toinen pelaa WASDilla ja toinen nuolinäppäimillä. Lisäksi kaksinpelissä on taistelutoiminnallisuuksi, kuten se, että mato voi ampua itsestään paloja toisen matoa kohti. Jos ammus osuu johonkin muuhun kuin päähän, katkeaa toisen mato (menettäen samalla pisteitä), ja jos ammus osuu päähän, kuolee vastapelaaja.

## Käyttäjät
Sovelluksessa on toiminnallisuus rekisteröityä ennen pelin alkua haluamallaan käyttäjätunnuksella, johon tallennetaan pelin jälkeen pistetilanne. Kaksinpelille ja yksinpelille on omat listansa pelin jälkeisistä tuloksista, ja vain 20 parasta käyttäjää tallennetaan.

## Käyttöliittymäluonnos
Sovellus koostuu kuudesta eri näkymästä. Ensimmäisenä on alkuvalikko, jossa päätetään haluaako yksin- vai kaksinpelin. Seuraavaksi kerrotaan ohjeet peliin ja annetaan käyttäjätunnukset pelaajalle tai pelaajille. Seuraavaksi on tietenkin itse pelikenttä. Pelin päätyttyä siirrytään tarkastelemaan kyseisen pelin pisteitä. Lopuksi voi vielä tarkastella top-10 highscoreja.

![Käyttöliittymäluonnos](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoliittymacrop.jpeg)

## Perusversion tarjoama toiminnallisuus
* Pelaaminen yksin tai kaksinpelinä. 
* Yksinpelissä voi valita haluaako nopeutuvat madon vai ei.
* Käyttäjätunnuksen antaminen ja mikäli pelitulos on tarpeeksi hyvä, sen tallentaminen tietokantaan.

## Jatkokehitysideoita
* Käyttäjätunnus ja salasana voisi olla hyvä, ettei väärinkäyttöjä käyttäjätunnuksissa tapahdu.
* Reunoista voisi mennä läpi toiselle puolelle kenttää.
* Vaikeustason voisi valita.
* Erilaisia karttoja jotka vaikeutuvat mitä pidemmälle pelissä pääsee.
* Kaksinpelissä ampumismahdollisuus tai jokin muu hauska ominaisuus.
* Testattu ja toimivaksi todettu parempi pisteidenlaskumenetelmä.
