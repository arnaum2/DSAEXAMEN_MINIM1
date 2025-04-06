package edu.upc.dsa.models;

public class Avio {
    private String id;
    private String model;
    private String companyiaAeria;

    public Avio(String id, String model, String companyiaAeria) {
        this.id = id;
        this.model = model;
        this.companyiaAeria = companyiaAeria;
    }

    public String getId() { return id; }
    public String getModel() { return model; }
    public String getCompanyiaAeria() { return companyiaAeria; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setModel(String model) { this.model = model; }
    public void setCompanyiaAeria(String companyiaAeria) { this.companyiaAeria = companyiaAeria; }

}
