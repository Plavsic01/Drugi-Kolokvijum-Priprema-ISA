package com.singidunum.ac.rs.priprema.service;

import com.singidunum.ac.rs.priprema.model.Proizvod;
import com.singidunum.ac.rs.priprema.repository.ProizvodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProizvodService {
    @Autowired
    private ProizvodRepository proizvodRepository;

    public Iterable<Proizvod> findAll(){
        return proizvodRepository.findAll();
    }

    public Proizvod findById(Long id){
        return proizvodRepository.findById(id).orElse(null);
    }

    public Proizvod create(Proizvod proizvod){
        return proizvodRepository.save(proizvod);
    }

    public Proizvod update(Proizvod proizvod){
        return proizvodRepository.save(proizvod);
    }

    public void delete(Long id){
        proizvodRepository.deleteById(id);
    }
}
