package com.singidunum.ac.rs.priprema.repository;

import com.singidunum.ac.rs.priprema.model.Kupac;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KupacRepository extends CrudRepository<Kupac,Long> {
    Optional<Kupac> findByKorisnickoIme(String korisnickoIme);
}
