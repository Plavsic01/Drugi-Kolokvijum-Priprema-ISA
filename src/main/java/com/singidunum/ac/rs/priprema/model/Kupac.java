package com.singidunum.ac.rs.priprema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Kupac extends Korisnik{

    private String adresa;
    @OneToMany(mappedBy = "kupac")
    private Set<StavkaKupovine> stavkaKupovine;

    public Kupac() {}

    public Kupac(Long id, String korisnickoIme, String lozinka) {
        super(id, korisnickoIme, lozinka);
    }

    public Kupac(Long id, String korisnickoIme, String lozinka, String adresa, Set<StavkaKupovine> stavkaKupovine) {
        super(id, korisnickoIme, lozinka);
        this.adresa = adresa;
        this.stavkaKupovine = stavkaKupovine;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Set<StavkaKupovine> getStavkaKupovine() {
        return stavkaKupovine;
    }

    public void setStavkaKupovine(Set<StavkaKupovine> stavkaKupovine) {
        this.stavkaKupovine = stavkaKupovine;
    }

    @Override
    public String toString() {
        return "Kupac{" +
                "adresa='" + adresa + '\'' +
                '}';
    }
}
