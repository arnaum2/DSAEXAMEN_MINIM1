package edu.upc.dsa;
import edu.upc.dsa.models.Avio;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Vol;


import java.util.Queue;

public interface GestioAeroport {
    void afegirAvio(String id, String model, String companyiaAeria);
    void afegirVol(String id, String avioId, String origen, String desti, String horaSortida, String horaArribada);
    int numVols();
    public Avio obtenirAvio(String id);
    public Vol obtenirVol(String id);
    public Maleta afegirMaleta(String usuari, String volId);
    Queue<Maleta> obtenirMaletesVol(String volId);

}