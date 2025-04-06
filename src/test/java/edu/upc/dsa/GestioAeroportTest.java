package edu.upc.dsa;

import static org.junit.Assert.*;
import edu.upc.dsa.models.Avio;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Vol;
import edu.upc.dsa.GestioAeroport;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

//import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;

import java.util.Queue;

public class GestioAeroportTest {

    GestioAeroport pm;

    @Before
    public void setUp() {
        Logger.getLogger(GestioAeroport.class).setLevel(Level.INFO);
        pm = GestioAeroportImpl.getInstance();
        pm.afegirVol("01", "AYBA1", "Barcelona", "Madrid", "13:30", "15:30");
        pm.afegirVol("02", "VY32A", "Barcelona", "Paris", "16:30", "18:30");
        pm.afegirAvio("AYBA1", "Airbus 380", "Vueling");
        pm.afegirAvio("VY32A", "Boeing 777", "Quatar Airways");

    }
    @After
    public void tearDown() {
        this.pm=null;
    }
    @Test
    public void testAfegirAvio() {

        pm.afegirAvio("AYBA1", "Airbus380", "Vueling");
        Avio avio = pm.obtenirAvio("AYBA1");
        assertNotNull(avio);
        Assert.assertEquals("Airbus380", avio.getModel());
        Assert.assertEquals("Vueling", avio.getCompanyiaAeria());

    }

    @Test
    public void testFacturarMaleta() {
        Maleta maleta = pm.afegirMaleta("Arnau", "01");
        assertNotNull(maleta);
        Assert.assertEquals("Arnau",maleta.getUsuari());
    }
    @Test
    public void testAfegirVol() {

        pm.afegirVol("01", "AYBA1", "Reus", "Girona", "9:00", "9:30");
        Vol vol = pm.obtenirVol("01");
        Assert.assertNotNull(vol);
        Assert.assertEquals("Reus", vol.getOrigen());
        Assert.assertEquals("Girona", vol.getDesti());
        Assert.assertEquals("9:00", vol.getHoraSortida());
        Assert.assertEquals("9:30", vol.getHoraArribada());
    }

    @Test
    public void testObtenirMaletes() {
        pm.afegirMaleta("Manola", "01");
        pm.afegirMaleta("Manuel", "01");
        Queue<Maleta> maletes = pm.obtenirMaletesVol("01");
        Assert.assertEquals(2, maletes.size());
        Maleta[] vectorMaletes = maletes.toArray(new Maleta[0]);
        Assert.assertEquals("Manola", vectorMaletes[0].getUsuari());
        Assert.assertEquals("Manuel", vectorMaletes[1].getUsuari());
    }
    @Test
    public void testModificarVol() {

        pm.afegirVol("01", "VY32A", "Barcelona", "Madrid", "11:00", "13:00");
        Vol v = pm.obtenirVol("01");
        Assert.assertNotNull(v);
        Assert.assertEquals("Barcelona", v.getOrigen());
        Assert.assertEquals("Madrid", v.getDesti());
        Assert.assertEquals("11:00", v.getHoraSortida());
        Assert.assertEquals("13:00", v.getHoraArribada());
    }
    @Test
    public void testModificarAvio() {
        pm.afegirAvio("01", "Boeing787", "Vueling");
        pm.afegirAvio("01", "Boeing777", "Iberia");
        Avio avio = pm.obtenirAvio("01");
        Assert.assertEquals("Boeing777", avio.getModel());
        Assert.assertEquals("Iberia", avio.getCompanyiaAeria());
    }


}