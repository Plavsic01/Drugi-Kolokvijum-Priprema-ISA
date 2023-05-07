package com.singidunum.ac.rs.priprema.dto;



public class StavkaKupovineDTO {
    private Long id;
    private Double kolicina;
    private ProizvodDTO proizvod;
    private KupacDTO kupac;

    public StavkaKupovineDTO() {}

    public StavkaKupovineDTO(Long id, Double kolicina, ProizvodDTO proizvod, KupacDTO kupac) {
        this.id = id;
        this.kolicina = kolicina;
        this.proizvod = proizvod;
        this.kupac = kupac;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getKolicina() {
        return kolicina;
    }

    public void setKolicina(Double kolicina) {
        this.kolicina = kolicina;
    }

    public ProizvodDTO getProizvod() {
        return proizvod;
    }

    public void setProizvod(ProizvodDTO proizvod) {
        this.proizvod = proizvod;
    }

    public KupacDTO getKupac() {
        return kupac;
    }

    public void setKupac(KupacDTO kupac) {
        this.kupac = kupac;
    }
}
