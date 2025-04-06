package edu.upc.dsa.models;

import java.util.List;

public class Maleta {


    private String id; // Identificador únic de la maleta (generat automàticament)
    private String usuari; // Usuari que factura la maleta
    //private Vol vol; // Vol al qual pertany la maleta

    // Constructor
    public Maleta(String id, String usuari) {
        this.id = id;
        this.usuari = usuari;
        //this.vol = vol;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getUsuari() {
        return usuari;
    }
    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }
    public void setId(String id) {
        this.id = id;
    }
}
