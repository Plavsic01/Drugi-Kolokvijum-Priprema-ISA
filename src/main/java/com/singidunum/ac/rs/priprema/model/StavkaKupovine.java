package com.singidunum.ac.rs.priprema.model;

import jakarta.persistence.*;

@Entity
public class StavkaKupovine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double kolicina;
    @ManyToOne
    private Proizvod proizvod;
    @ManyToOne
    private Kupac kupac;

    public StavkaKupovine() {}

    public StavkaKupovine(Long id, Double kolicina, Proizvod proizvod) {
        this.id = id;
        this.kolicina = kolicina;
        this.proizvod = proizvod;
    }

    public StavkaKupovine(Long id, Double kolicina, Proizvod proizvod, Kupac kupac) {
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

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    @Override
    public String toString() {
        return "StavkaKupovine{" +
                "id=" + id +
                ", kolicina=" + kolicina +
                ", proizvod=" + proizvod +
                ", kupac=" + kupac +
                '}';
    }
}
