package com.singidunum.ac.rs.priprema.repository;

import com.singidunum.ac.rs.priprema.model.Korisnik;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KorisnikRepository extends CrudRepository<Korisnik,Long> {
    Optional<Korisnik> findByKorisnickoIme(String korisnickoIme);

}
