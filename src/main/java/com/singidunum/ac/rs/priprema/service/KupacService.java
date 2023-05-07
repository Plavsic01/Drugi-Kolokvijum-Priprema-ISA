package com.singidunum.ac.rs.priprema.service;

import com.singidunum.ac.rs.priprema.model.Korisnik;
import com.singidunum.ac.rs.priprema.model.Kupac;
import com.singidunum.ac.rs.priprema.repository.KorisnikRepository;
import com.singidunum.ac.rs.priprema.repository.KupacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KupacService {
    @Autowired
    private KupacRepository kupacRepository;

    public Iterable<Kupac> findAll(){
        return kupacRepository.findAll();
    }

    public Kupac findById(Long id){
        return kupacRepository.findById(id).orElse(null);
    }

    public Kupac findByKorisnickoIme(String korisnickoIme){
        return kupacRepository.findByKorisnickoIme(korisnickoIme).orElse(null);
    }

    public Kupac create(Kupac kupac){
        return kupacRepository.save(kupac);
    }

    public Kupac update(Kupac kupac){
        return kupacRepository.save(kupac);
    }

    public void delete(Long id){
        kupacRepository.deleteById(id);
    }
}
