package edu.upc.dsa;
import java.util.List;
//import edu.upc.dsa.exceptions.AvionNotFoundException;
//import edu.upc.dsa.exceptions.VueloNotFoundException;
import edu.upc.dsa.models.Avio;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Vol;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.*;


public class GestioAeroportImpl implements GestioAeroport {
    private static GestioAeroportImpl instance = null;
    private static final Logger logger = Logger.getLogger(GestioAeroportImpl.class);

    // Estructures de dades
    private final Map<String, Avio> avions;
    private final Map<String, Vol> vols;
    private int contadorMaletes=0; // Per generar identificadors únics de maletes

    // Constructor privat (patró Singleton)
   public GestioAeroportImpl() {
        avions = new HashMap<>();
        vols = new HashMap<>();

    }

    // Mètode per obtenir la instància única (Singleton)
    public static GestioAeroportImpl getInstance() {
        if (instance == null) {
            instance = new GestioAeroportImpl();
        }
        return instance;
    }

    // 1️⃣ Afegir un avió al sistema
    @Override
    public void afegirAvio(String id, String model, String companyiaAeria) {
        logger.info("Inici afegir Avio: id=" + id + ", model=" + model + ", companyiaAeria=" + companyiaAeria);
        avions.put(id, new Avio(id, model, companyiaAeria));
        logger.info("Avió afegit correctament.");

    }

    // 2️⃣ Afegir un vol al sistema
    @Override
    public void afegirVol(String id, String avio, String origen, String desti, String horaSortida, String horaArribada) {
        logger.info("Afegint Vol:" +id+" model: "+avio) ;

        // Comprovem si l'aviació existeix
        if (!avions.containsKey(avio)) {
            logger.error("Error: L'avió amb id"+avio+" no existeix.");
            return;
            // throw new AvionNotFoundException; // Si l'aviació no existeix, no continuem
        }
        Vol vol = new Vol(id,avions.get(avio), origen, desti, horaSortida, horaArribada);
        vols.put(id, vol);
        logger.info("Vol id" +id+" afegit correctament.");


        // Comprovem si el vol ja existeix
        /*if (vols.containsKey(id)) {
            logger.info("Vol ja existent, actualitzant dades.");
            Vol vol = vols.get(id);
            vol.setAvio(avio);
            vol.setOrigen(origen);
            vol.setDesti(desti);
            vol.setHoraSortida(horaSortida);
            vol.setHoraArribada(horaArribada);
            logger.info("Vol actualitzat correctament.");
        } else {
            // Si el vol no existeix, creem un nou vol
            vols.put(avioId, new Vol(id, avio, origen, desti, horaSortida, horaArribada));
            logger.info("Vol afegit correctament.");
        }*/
    }
    @Override
    public int numVols(){
        return vols.size();
    }
    @Override
    public Avio obtenirAvio(String id) {
        return avions.get(id);
    }

    @Override
    public Vol obtenirVol(String id) {
        return vols.get(id);
    }

    @Override
    public Maleta afegirMaleta(String usuari, String volId) {
        if(!vols.containsKey(volId)) {
            logger.error("Error: L'vol id "+volId+" no existeix.");

        }
        Vol v = vols.get(volId);
        Maleta maleta = new Maleta(volId+"#"+contadorMaletes,usuari);
        contadorMaletes++;
        v.getMaletes().add(maleta);
        logger.info("Maleta d'usuari "+usuari+" en vol "+volId+" afegida correctament.");
        return maleta;
    }

    // 3️⃣ Facturar una maleta per un usuari
    /*@Override
    public void facturarMaleta(String usuari, String volId) {
        logger.info("Inici facturarMaleta: usuari=" + usuari + ", volId=" + volId);

        if (!vols.containsKey(volId)) {
            logger.error("Error: El vol amb id " + volId + " no existeix.");
            return;
        }
        Vol vol = vols.get(volId);
        String idMaleta = "M" + contadorMaletes++;
        Maleta maleta = new Maleta(idMaleta, usuari, vol);
        vol.afegirMaleta(maleta);

        logger.info("Maleta facturada correctament: " + maleta);
    }*/

    // 4️⃣ Obtenir la llista de maletes d’un vol
    @Override
    public Queue<Maleta> obtenirMaletesVol(String volId) {
        if(!vols.containsKey(volId)) {
            logger.error("Error: L'vol id "+volId+" no existeix.");
            return null;
        }
        Vol v = vols.get(volId);
        return v.getMaletes();
    }


}