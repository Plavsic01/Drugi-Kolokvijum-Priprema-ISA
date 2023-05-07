package com.singidunum.ac.rs.priprema.service;


import com.singidunum.ac.rs.priprema.model.StavkaKupovine;
import com.singidunum.ac.rs.priprema.repository.StavkaKupovineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StavkaKupovineService {
    @Autowired
    private StavkaKupovineRepository stavkaKupovineRepository;

    public Iterable<StavkaKupovine> findAll(){
        return stavkaKupovineRepository.findAll();
    }

//    public Optional<StavkaKupovine> findById(Long id){
//        return stavkaKupovineRepository.findById(id).orElse(null));
//    }

    //promeniti na optional za proizvod i kupac
    public StavkaKupovine findById(Long id){
        return stavkaKupovineRepository.findById(id).orElse(null);
    }

    public StavkaKupovine create(StavkaKupovine stavkaKupovine){
        return stavkaKupovineRepository.save(stavkaKupovine);
    }

    public StavkaKupovine update(StavkaKupovine stavkaKupovine){
        return stavkaKupovineRepository.save(stavkaKupovine);
    }

    public void delete(Long id){
        stavkaKupovineRepository.deleteById(id);
    }
}
