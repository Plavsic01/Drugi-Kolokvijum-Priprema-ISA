package com.singidunum.ac.rs.priprema.service;

import com.singidunum.ac.rs.priprema.model.PravoPristupa;
import com.singidunum.ac.rs.priprema.repository.PravoPristupaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PravoPristupaService {
    @Autowired
    private PravoPristupaRepository pravoPristupaRepository;

    public Iterable<PravoPristupa> findAll(){
        return pravoPristupaRepository.findAll();
    }

    public PravoPristupa findById(Long id){
        return pravoPristupaRepository.findById(id).orElse(null);
    }

    public PravoPristupa create(PravoPristupa pravoPristupa){
        return pravoPristupaRepository.save(pravoPristupa);
    }

    public PravoPristupa update(PravoPristupa pravoPristupa){
        return pravoPristupaRepository.save(pravoPristupa);
    }

    public void delete(Long id){
        pravoPristupaRepository.deleteById(id);
    }
}
