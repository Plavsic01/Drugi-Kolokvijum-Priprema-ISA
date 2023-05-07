package com.singidunum.ac.rs.priprema.repository;

import com.singidunum.ac.rs.priprema.model.Proizvod;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProizvodRepository extends CrudRepository<Proizvod,Long> {
}
