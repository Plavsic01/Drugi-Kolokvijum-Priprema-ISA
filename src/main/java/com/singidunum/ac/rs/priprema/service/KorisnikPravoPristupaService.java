package com.singidunum.ac.rs.priprema.service;

import com.singidunum.ac.rs.priprema.model.KorisnikPravoPristupa;
import com.singidunum.ac.rs.priprema.repository.KorisnikPravoPristupaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KorisnikPravoPristupaService {

    @Autowired
    private KorisnikPravoPristupaRepository korisnikPravoPristupaRepository;

    public Iterable<KorisnikPravoPristupa> findAll(){
        return korisnikPravoPristupaRepository.findAll();
    }

    public KorisnikPravoPristupa findById(Long id){
        return korisnikPravoPristupaRepository.findById(id).orElse(null);
    }

    public KorisnikPravoPristupa create(KorisnikPravoPristupa korisnikPravoPristupa){
        return korisnikPravoPristupaRepository.save(korisnikPravoPristupa);
    }

    public KorisnikPravoPristupa update(KorisnikPravoPristupa korisnikPravoPristupa){
        return korisnikPravoPristupaRepository.save(korisnikPravoPristupa);
    }

    public void delete(Long id){
        korisnikPravoPristupaRepository.deleteById(id);
    }
}
