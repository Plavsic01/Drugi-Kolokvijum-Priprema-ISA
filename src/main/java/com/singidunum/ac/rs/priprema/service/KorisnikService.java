package com.singidunum.ac.rs.priprema.service;

import com.singidunum.ac.rs.priprema.model.Korisnik;
import com.singidunum.ac.rs.priprema.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    public Iterable<Korisnik> findAll(){
        return korisnikRepository.findAll();
    }

    public Korisnik findById(Long id){
        return korisnikRepository.findById(id).orElse(null);
    }

    public Korisnik create(Korisnik korisnik){
        return korisnikRepository.save(korisnik);
    }

    public Korisnik update(Korisnik korisnik){
        return korisnikRepository.save(korisnik);
    }

    public void delete(Long id){
        korisnikRepository.deleteById(id);
    }

    public Optional<Korisnik> findByKorisnickoIme(String korisnickoIme){
        return this.korisnikRepository.findByKorisnickoIme(korisnickoIme);
    }

}
