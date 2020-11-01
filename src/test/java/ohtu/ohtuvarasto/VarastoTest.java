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

    // käytettävissä kaikissa testeissä, luodaan @Before-merkityssä metodissa
    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriEiLuoNegatiivisenTilavuudenVarastoa() {
        assertEquals(0, new Varasto(-1).getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void alkuSaldollinenKonstruktori() {
        Varasto varasto2 = new Varasto(11, 2);
        assertEquals(11, varasto2.getTilavuus(), vertailuTarkkuus);
        assertEquals(2, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkuSaldollinenKonstruktoriYlimaara() {
        Varasto varasto2 = new Varasto(11, 12);
        assertEquals(11, varasto2.getTilavuus(), vertailuTarkkuus);
        assertEquals(11, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkuSaldollinenKonstruktoriEiLuoNegatiivisenTilavuudenVarastoa() {
        assertEquals(0, new Varasto(-1, -1).getTilavuus(), vertailuTarkkuus);
    }


    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysEiYlitaTilavuutta() {
        varasto.lisaaVarastoon(20);

        // saldon pitäisi olla sama kun tilavuus
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiLisaaSaldoa() {
        varasto.lisaaVarastoon(-8);

        // saldon pitäisi olla sama kun aluksi
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
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
    public void yliOttaminenPalauttaaOikeanMaaran() {
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
    public void negatiivinenOttaminenEiLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(-2);

        // varastossa pitäisi olla tilaa 10 - 8 + 0 eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void merkkijonoesitys() {
        Varasto varasto2 = new Varasto(109, 23);

        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
        assertEquals("saldo = 23.0, vielä tilaa 86.0", varasto2.toString());
    }
}
