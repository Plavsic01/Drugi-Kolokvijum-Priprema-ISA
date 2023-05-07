package com.singidunum.ac.rs.priprema.dto;


import java.util.HashSet;
import java.util.Set;

public class KupacDTO extends KorisnikDTO{
    private String adresa;
    private Set<StavkaKupovineDTO> stavkaKupovine;

    public KupacDTO() {}

    public KupacDTO(Long id, String korisnickoIme, String lozinka,String adresa) {
        super(id, korisnickoIme, lozinka);
        this.adresa = adresa;
    }

    public KupacDTO(Long id, String korisnickoIme, String lozinka,Set<KorisnikPravoPristupaDTO> korisnikPravoPristupa,String adresa) {
        super(id, korisnickoIme, lozinka,korisnikPravoPristupa);
        this.adresa = adresa;
    }


    public KupacDTO(Long id, String korisnickoIme, String lozinka, Set<KorisnikPravoPristupaDTO> korisnikPravoPristupa, String adresa, Set<StavkaKupovineDTO> stavkaKupovine) {
        super(id, korisnickoIme, lozinka, korisnikPravoPristupa);
        this.adresa = adresa;
        this.stavkaKupovine = stavkaKupovine;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Set<StavkaKupovineDTO> getStavkaKupovine() {
        return stavkaKupovine;
    }

    public void setStavkaKupovine(Set<StavkaKupovineDTO> stavkaKupovine) {
        this.stavkaKupovine = stavkaKupovine;
    }
}
