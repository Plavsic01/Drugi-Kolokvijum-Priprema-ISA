package com.singidunum.ac.rs.priprema.repository;

import com.singidunum.ac.rs.priprema.model.PravoPristupa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PravoPristupaRepository extends CrudRepository<PravoPristupa,Long> {
}
