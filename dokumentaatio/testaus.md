# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Automatisoitujen testien ytimen muodostavat sovelluslogiikka, eli pakkauksen [snake.parts](https://github.com/hallssus/omt-harjoitustyo/tree/master/Snake/src/main/java/snake/parts) luokkia testaavat integraatiotestit [PieceTest](https://github.com/hallssus/omt-harjoitustyo/blob/master/Snake/src/test/java/snake/parts/PieceTest.java), [WormTest](https://github.com/hallssus/omt-harjoitustyo/blob/master/Snake/src/test/java/snake/parts/WormTest.java) sekä [SnakeTest](https://github.com/hallssus/omt-harjoitustyo/blob/master/Snake/src/test/java/snake/parts/SnakeTest.java) joiden määrittelevät testitapaukset simuloivat ohjelman toiminnallisuuksia.

### DAO-luokat

DAO-luokat testataan [ScoreDaoTest](https://github.com/hallssus/omt-harjoitustyo/blob/master/Snake/src/test/java/snake/database/ScoreDaoTest.java)-luokan avulla. Testiluokassa luodaan vain testaamiseen käytetty testdatabase. 

### Testauskattavuus

Testaamatta jäivät tilanteet DAOssa, jotka liittyvät virheisiin jota tulee jos kyseinen database on jo olemassa.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellus on haettu ja sitä on testattu [käyttöohjeen](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) kuvaamalla tavalla Linux-ympäristöön.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/hallssus/omt-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. 

## Sovellukseen jääneet laatuongelmat

Tyhjät pelaajan nimet ovat sallittuja toistaiseksi.

