package com.singidunum.ac.rs.priprema.dto;

import java.util.Set;

public class ProizvodDTO {
    private Long id;
    private String naziv;
    private Double cena;
    private Set<StavkaKupovineDTO> stavkaKupovine;

    public ProizvodDTO() {}

    public ProizvodDTO(Long id, String naziv, Double cena) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
    }

    public ProizvodDTO(Long id, String naziv, Double cena, Set<StavkaKupovineDTO> stavkaKupovine) {
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

    public Set<StavkaKupovineDTO> getStavkaKupovine() {
        return stavkaKupovine;
    }

    public void setStavkaKupovine(Set<StavkaKupovineDTO> stavkaKupovine) {
        this.stavkaKupovine = stavkaKupovine;
    }
}
