package com.singidunum.ac.rs.priprema.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Proizvod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;
    private Double cena;
    @OneToMany(mappedBy = "proizvod")
    private Set<StavkaKupovine> stavkaKupovine;

    public Proizvod() {}

    public Proizvod(Long id, String naziv, Double cena) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
    }

    public Proizvod(Long id, String naziv, Double cena, Set<StavkaKupovine> stavkaKupovine) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
        this.stavkaKupovine = stavkaKupovine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Set<StavkaKupovine> getStavkaKupovine() {
        return stavkaKupovine;
    }

    public void setStavkaKupovine(Set<StavkaKupovine> stavkaKupovine) {
        this.stavkaKupovine = stavkaKupovine;
    }

    @Override
    public String toString() {
        return "Proizvod{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", cena=" + cena +
                '}';
    }
}

