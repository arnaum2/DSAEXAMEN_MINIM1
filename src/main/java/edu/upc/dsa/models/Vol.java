package edu.upc.dsa.models;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

public class Vol {

    private String id; // Identificador únic del vol
    private Avio avio; // Avió assignat al vol
    private String origen;
    private String desti;
    private String horaSortida;
    private String horaArribada;
    private Queue<Maleta> maletes; // Pila per gestionar l'ordre LIFO de les maletes


    // Constructor
    public Vol(String id, Avio avio, String origen, String desti, String horaSortida, String horaArribada) {
        this.id = id;
        this.avio = avio;
        this.origen = origen;
        this.desti = desti;
        this.horaSortida = horaSortida;
        this.horaArribada = horaArribada;
        this.maletes = new LinkedList<>();
    }



    // Getters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Avio getAvio() { return avio; }
    public String getOrigen() { return origen; }
    public String getDesti() { return desti; }
    public String getHoraSortida() { return horaSortida; }
    public String getHoraArribada() { return horaArribada; }
    public Queue<Maleta> getMaletes() { return maletes; }
    public void setAvio(Avio avio){
        this.avio=avio;
    }
    public void setOrigen(String origen){
        this.origen=origen;
    }
    public void setDesti(String desti){
        this.desti=desti;
    }
    public void setHoraSortida(String horaSortida){
        this.horaSortida=horaSortida;

    }
    public void setHoraArribada(String horaArribada){
        this.horaArribada=horaArribada;
    }
    // Metode per afegir una maleta a la pila
    public void afegirMaleta(Maleta maleta) {
        maletes.add(maleta);
    }


}
