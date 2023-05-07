package com.singidunum.ac.rs.priprema.dto;


import com.singidunum.ac.rs.priprema.model.KorisnikPravoPristupa;

import java.util.HashSet;
import java.util.Set;

public class KorisnikDTO {
    private Long id;
    private String korisnickoIme;
    private String lozinka;
    private Set<KorisnikPravoPristupaDTO> korisnikPravoPristupa;

    public KorisnikDTO() {}

    public KorisnikDTO(Long id, String korisnickoIme, String lozinka) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public KorisnikDTO(Long id, String korisnickoIme,String lozinka,Set<KorisnikPravoPristupaDTO> korisnikPravoPristupa) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.korisnikPravoPristupa = korisnikPravoPristupa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Set<KorisnikPravoPristupaDTO> getKorisnikPravoPristupa() {
        return korisnikPravoPristupa;
    }

    public void setKorisnikPravoPristupa(Set<KorisnikPravoPristupaDTO> korisnikPravoPristupa) {
        this.korisnikPravoPristupa = korisnikPravoPristupa;
    }
}
