# Arkkitehtuurikuvaus

## Rakenne
Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:

Pakkaus *snake.ui* sisältää JavaFX:llä toteuteus graafisen käyttöliittymän, *snake.parts* sovelluslogiikan ja *snake.database* tietojen pysyväistallennusta vastaavan koodin.

## Käyttöliittymä
Käyttöliittymä sisältää kuusi erillistä näkymää:
* Alkuvalikko, jossa päätetään haluaako pelata yksin vai kaverin kanssa
* Pelaajan tai pelaajien käyttäjätunnusten kirjoittaminen
* Ohjeet
* Peli
* Pelaajien pisteet
* Kymmenen parasta tulosta kaikista peleistä koskaan

Nämä on toteutettu Scene-olioilla. Näkymistä jokainen on yksi kerrallaan näkyvissä. Käyttöliittymä rakentuu ohjelmallisesti luokassa [snake.ui.SnakeGame](https://github.com/hallssus/omt-harjoitustyo/blob/master/Snake/src/main/java/snake/ui/SnakeGame.java).

Käyttöliittymä on pyritty eristämään sovelluslogiikasta.

### Sovelluslogiikka

Sovelluksen toiminnallisista kokonaisuuksista vastaa luokka Snake, joka rakentaa koko matopelin. Mato määritellään luokassa Worm ja se koostuu Piece-luokasta, eli paloista. 

Luokka ScoreDao huolehtii tulosten kirjaamisesta tietokantaan ja sillä on myös metodeja parhaiden tulosten hakemisesta sieltä.



Luokka-/pakkauskaavio:
![Luokkakaavio](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/kuvat/pakettikaavio.png)

### Tietojen pysyväistallennus
Pakkauksen snake.database luokat ScoreDao, Database sekä Collector hoitavat tietojen tallennuksen tietokantaan. 


### Päätoiminnallisuudet

#### Snake-pelin update

Snake-luokan update-metodin sekvenssikaavio:
![Sekvenssikaavio](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/kuvat/Snake.png)

Metodi siis tarkistaa onko mato osunut johonkin ja mikäli ei ole osunut mihinkään, se liikkuu omaan suuntaansa.


## Ohjelman rakenteeseen jääneet heikkoudet
### Sovellus
Jos matoja liikuttaa nopeammin kuin niuden animointi tapahtuu, saattaa törmätä itseensä, sillä suunta vaihtuu, mutta sitä ei ehditä vielä animoimaan. En päässyt tästä bugista eroon.
Pelaajien nimet voivat olla jo valmiiksi tietokannassa, tai olla vaikka tyhjät, mikä on tietysti tyhmää.
Pistelaskussa on toistaiseksi parantamisen varaa. 
### Käyttöliittymä
Käyttöliittymä on kenties vähän tönkkö, eikä niin nätti kuin voisi.
