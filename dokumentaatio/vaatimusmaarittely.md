# Vaatimusmäärittely

## Sovelluksen tarkoitus
Sovellus on jo kaikille tuttu Matopeli pienellä tvistillä. Peliä voi pelata joko yksin, jolloin se toimii normaalin matopelin tapaa WASDilla, tai kaksin kaveria vastaan samalla koneella, jolloin toinen pelaa WASDilla ja toinen nuolinäppäimillä. Lisäksi kaksinpelissä on taistelutoiminnallisuuksi, kuten se, että mato voi ampua itsestään paloja toisen matoa kohti. Jos ammus osuu johonkin muuhun kuin päähän, katkeaa toisen mato (menettäen samalla pisteitä), ja jos ammus osuu päähän, kuolee vastapelaaja.
## Käyttäjät
Sovelluksessa on toiminnallisuus rekisteröityä ennen pelin alkua haluamallaan käyttäjätunnuksella, johon tallennetaan pelin jälkeen pistetilanne. Kaksinpelille ja yksinpelille on omat listansa pelin jälkeisistä tuloksista, ja vain 20 parasta käyttäjää tallennetaan.
## Käyttöliittymäluonnos
Sovellus koostuu kolmesta eri näkymästä. Ensimmäisenä on alkuvalikko, jossa päätetään haluaako yksin- vai kaksinpelin antamalla tekstikenttiin joko yhden tai kaksi käyttäjätunnusta. Seuraavaksi on tietenkin itse pelikenttä. Pelin päätyttyä siirrytään pistetilannenäkymään, jossa listataan 20 parasta käyttäjätunnusta pisteineen.
![Käyttöliittymäluonnos](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoliittymacrop.jpeg)
## Perusversion tarjoama toiminnallisuus
Mikäli käyttäjätunnus on jo annettu aikaisemmassa pelissä, oletetaan kyseessä olevan sama tyyppi ja kirjoitetaan uuden pelin tulos aikaisemma päälle, mikäli aikaisemmat pisteet on ylitetty. 
## Jatkokehitysideoita
Käyttäjätunnus ja salasana voisi olla hyvä, ettei väärinkäyttöjä käyttäjätunnuksissa tapahdu. Se ei kuitenkaan ole oleellisin osa toistaiseksi joten katsotaan, saanko sen aikaiseksi vielä tämän jakson aikana.

