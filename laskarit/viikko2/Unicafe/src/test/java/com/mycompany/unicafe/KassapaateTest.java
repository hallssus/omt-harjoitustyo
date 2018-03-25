/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hallssus
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @After
    public void tearDown() {
    }
    //tehtävänannossa sanottiin, että rahaa piti olla alussa 1000
    //joten tein juuri niin, toivottavasti tämä ei ollut virhe
    @Test
    public void luodunKassapaatteenRahamaaraOnOikea(){
        assertTrue(1000 == kassa.kassassaRahaa());
    }
    
    @Test
    public void luodunKassanEdullisiaLounaitaMyytyOikeaMaara(){
        assertTrue(0 == kassa.edullisiaLounaitaMyyty());
    } 
    
    @Test
    public void luodunKassanMaukkaitaLounaitaMyytyOikeaMaara(){
        assertTrue(0 == kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassanSaldoNouseeJosKateisMaksuRiittavaEdulliseen(){
        kassa.syoEdullisesti(500); //5e
        assertTrue(1000 + 240 == kassa.kassassaRahaa());
    }
    
    @Test
    public void kassanSaldoNouseeJosKateisMaksuRiittavaMaukkaaseen(){
        kassa.syoMaukkaasti(500); //5e
        assertTrue(1000 + 400 == kassa.kassassaRahaa());
    }
    
    @Test
    public void vaihtoRahanSuuruusOikeaEdullisessaJosRahaaRiittavasti(){
        int vaihtoraha = kassa.syoEdullisesti(500);
        assertTrue(500-240 == vaihtoraha);
    }
    
    @Test
    public void vaihtoRahanSuuruusOikeaMaukkaassaJosRahaaRiittavasti(){
        int vaihtoraha = kassa.syoMaukkaasti(500);
        assertTrue(500-400 == vaihtoraha);
    }
    
    @Test
    public void edullistenLounaidenMaaraKasvaaKateisOstossaJosRahaaRiittavasti(){
        kassa.syoEdullisesti(500);
        kassa.syoEdullisesti(500);
        assertTrue(2 == kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenLounaidenMaaraKasvaaKateisOstossaJosRahaaRiittavasti(){
        kassa.syoMaukkaasti(500);
        kassa.syoMaukkaasti(500);
        assertTrue(2 == kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassanRahaMaaraEiMuutuJosKateisMaksuEiRiitaEdulliseen(){
        kassa.syoEdullisesti(200);
        assertTrue(1000 == kassa.kassassaRahaa());
    }
    
    @Test
    public void kassanRahaMaaraEiMuutuJosKateisMaksuEiRiitaMaukkaaseen(){
        kassa.syoMaukkaasti(200);
        assertTrue(1000 == kassa.kassassaRahaa());
    }
    
    @Test 
    public void kaikkiRahatPalautetaanVaihtorahanaJosRahaEiRiitaEdulliseen(){
        assertTrue(200 == kassa.syoEdullisesti(200));
    }
    
    @Test 
    public void kaikkiRahatPalautetaanVaihtorahanaJosRahaEiRiitaMaukkaaseen(){
        assertTrue(200 == kassa.syoMaukkaasti(200));
    }
    
    @Test
    public void edullistenLounaidenMaaraEiMuutuJosKateistaEiTarpeeksi(){
        kassa.syoEdullisesti(200);
        assertTrue(0 == kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenLounaidenMaaraEiMuutuJosKateistaEiTarpeeksi(){
        kassa.syoMaukkaasti(200);
        assertTrue(0 == kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaTarpeeksiRahaaEdulliseenVeloitetaanSummaKortilta(){
        kassa.syoEdullisesti(kortti);
        assertTrue(1000-240 == kortti.saldo());
    }
    
    @Test
    public void josKortillaTarpeeksiRahaaMaukkaaseenVeloitetaanSummaKortilta(){
        kassa.syoMaukkaasti(kortti);
        assertTrue(1000-400 == kortti.saldo());
    }
    
    @Test
    public void josKortillaTarpeeksiRahaaEdulliseenPalautetaanTrue(){
        assertTrue(true == kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void josKortillaTarpeeksiRahaaMaukkaaseenPalautetaanTrue(){
        assertTrue(true == kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void josKortillaTarpeeksiRahaaEdullistenLukumaaraKasvaa(){
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertTrue(2 == kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaTarpeeksiRahaaMaukkaidenLukumaaraKasvaa(){
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertTrue(2 == kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaEiRahaaEdulliseenKortinRahamaaraEiMuutu(){
        kassa.syoMaukkaasti(kortti); //600
        kassa.syoMaukkaasti(kortti); //200
        kassa.syoEdullisesti(kortti);
        assertTrue(200 == kortti.saldo());
    }
    
    @Test
    public void josKortillaEiRahaaMaukkaaseenKortinRahamaaraEiMuutu(){
        kassa.syoMaukkaasti(kortti); //600
        kassa.syoMaukkaasti(kortti); //200
        kassa.syoMaukkaasti(kortti);
        assertTrue(200 == kortti.saldo());
    }
    
    @Test
    public void josKortillaEiRahaaEdulliseenPalautetaanFalse(){
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertTrue(false == kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void josKortillaEiRahaaMaukkaaseenPalautetaanFalse(){
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertTrue(false == kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void josKortillaEiRahaaEiEdullistenLukumaaraMuutu(){
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        assertTrue(0 == kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaEiRahaaEiMaukkaidenLukumaaraMuutu(){
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertTrue(2 == kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassassaOlevaRahamaaraEiMuutuKortillaOstettaessa(){
        kassa.syoEdullisesti(kortti);
        assertTrue(1000 == kassa.kassassaRahaa());
    }
    
    @Test
    public void kortilleLadatessaKortinSaldoMuuttuuOikeallaSummalla(){
        kassa.lataaRahaaKortille(kortti, 200);
        assertTrue(1200 == kortti.saldo());
    }
    
    @Test
    public void kortilleLadattaessaKassanSaldoMuuttuuOikeallaSummalla(){
        kassa.lataaRahaaKortille(kortti, 200);
        assertTrue(1200 == kassa.kassassaRahaa());
    }
    
    @Test
    public void kortilleLadattaessaNegatiivistaEiTapahtuMitaan(){
        kassa.lataaRahaaKortille(kortti, -20);
        assertTrue(1000 == kortti.saldo());
    }
    
}
