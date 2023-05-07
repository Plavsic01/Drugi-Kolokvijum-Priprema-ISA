package com.singidunum.ac.rs.priprema.repository;

import com.singidunum.ac.rs.priprema.model.KorisnikPravoPristupa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikPravoPristupaRepository extends CrudRepository<KorisnikPravoPristupa,Long> {
}
