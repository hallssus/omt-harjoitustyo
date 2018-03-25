package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein(){
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein(){
        kortti.lataaRahaa(20); //0.30 pitäs olla
        assertEquals("saldo: 0.30", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikeinJosRahaaTarpeeksi(){
        kortti.otaRahaa(5); //pitäs olla 0.05 e
        assertEquals("saldo: 0.05", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi(){
        kortti.otaRahaa(20); //pitäis olla 0.10e
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void metodiPalauttaaTrueJosRahatRiittivat(){
        
        boolean on = kortti.otaRahaa(1); //pitäis olla true       
        assertEquals(true, on);
    }
    
    public void metodiPalauttaaFalseJosEiRiitaRahat(){
        boolean ei = kortti.otaRahaa(200); //pitäis olla false
        assertEquals(false, ei);
    }
    
    @Test
    public void saldoMetodiPalauttaaOikein(){
        assertTrue(10==kortti.saldo());
    }
}
