# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Automatisoitujen testien ytimen muodostavat sovelluslogiikkaa, eli pakkauksen [snake.parts](https://github.com/hallssus/omt-harjoitustyo/tree/master/Snake/src/main/java/snake/parts) luokkia testaavat testit [PieceTest](https://github.com/hallssus/omt-harjoitustyo/blob/master/Snake/src/test/java/snake/parts/PieceTest.java), [WormTest](https://github.com/hallssus/omt-harjoitustyo/blob/master/Snake/src/test/java/snake/parts/WormTest.java) sekä [SnakeTest](https://github.com/hallssus/omt-harjoitustyo/blob/master/Snake/src/test/java/snake/parts/SnakeTest.java), joiden määrittelemät testitapaukset simuloivat ohjelman toiminnallisuuksia. 

### DAO-luokat

DAO-luokat testataan [ScoreDaoTest](https://github.com/hallssus/omt-harjoitustyo/blob/master/Snake/src/test/java/snake/database/ScoreDaoTest.java)-luokan avulla. Testiluokassa luodaan vain testaamiseen käytetty testdatabase, joka testien jälkeen tyhjennetään. 

### Testauskattavuus

Käyttöliittymää *SnakeGame* lukuunottamatta sovelluksen testauksen rivikattavuudeksi tuli 94% ja haaraumakattavuudeksi 87%.

![Testikattavuus](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/kuvat/testikattavuus.png)

Testaamatta jäivät osa Snake-luokan update-metodin tilanteista, jotka ovat suht yksinkertaisia, mutta vievät todella paljon aikaa testata. 

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellus on haettu ja sitä on testattu [käyttöohjeen](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) kuvaamalla tavalla Linux-ympäristöön.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. 

## Sovellukseen jääneet laatuongelmat

Tyhjät pelaajan nimet ovat sallittuja toistaiseksi. Pelaajien nimissä ei saa olla kaksoispiste-merkkiä ":", sillä sitä käytetään kun tulostetaan asioita databasesta. Tämä siis tarkistetaan ohjelmassa, muttei minkäänlaista virheilmoitusta tule jos käyttäjä yrittää syöttää tällasen nimen, ohjelma ei vain mene eteenpäin. Tätä voisi selkeyttää, sillä kaikki eivät kuitenkaan jaksa lukea käyttöohjeita.

