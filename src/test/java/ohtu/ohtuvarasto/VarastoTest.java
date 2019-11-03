package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto alkusaldoVarasto;
    Varasto nollavarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoNollavaraston() {
        nollavarasto = new Varasto(-10);
        assertEquals(0, nollavarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoKonstruktoriAsettaaAnnetunTilavuuden() {
        alkusaldoVarasto = new Varasto(10, 8);
        assertEquals(10, alkusaldoVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoKonstruktoriNollaaNegTilavuuden() {
        alkusaldoVarasto = new Varasto(-10, 8);
        assertEquals(0, alkusaldoVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoKonstruktoriNollaaNegAlkusaldon() {
        alkusaldoVarasto = new Varasto(10, -8);
        assertEquals(0, alkusaldoVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoKonstruktoriAsettaaAlkusaldonKunMahtuu() {
        alkusaldoVarasto = new Varasto(10, 8);
        assertEquals(8, alkusaldoVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoKonstruktoriTayttaaVarastonJaHukkaaLoput() {
        alkusaldoVarasto = new Varasto(10, 12);
        assertEquals(10, alkusaldoVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-8);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void liianSuuriLisaysEiKasvataSaldoaYliTilavuuden() {
        varasto.lisaaVarastoon(12);
        
        assertEquals(varasto.getSaldo(), varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenEiMahdollista() {
        varasto.lisaaVarastoon(8);
        
        double saatuMaara = varasto.otaVarastosta(-2);
        
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void ylisuuriOttaminenPalauttaaSaldon() {
        varasto.lisaaVarastoon(8);
        
        double saatuMaara = varasto.otaVarastosta(10);
        
        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void toStrigToimii() {
        String oletettu = "saldo = 0.0, vielä tilaa 10";
        assertEquals(oletettu, varasto.toString());
    }

}