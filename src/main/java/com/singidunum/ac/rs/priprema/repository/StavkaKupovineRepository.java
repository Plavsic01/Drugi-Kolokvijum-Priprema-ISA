package com.singidunum.ac.rs.priprema.repository;

import com.singidunum.ac.rs.priprema.model.StavkaKupovine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StavkaKupovineRepository extends CrudRepository<StavkaKupovine,Long> {
}
