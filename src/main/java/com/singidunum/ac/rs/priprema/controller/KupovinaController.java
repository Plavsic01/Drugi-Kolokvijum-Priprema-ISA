package com.singidunum.ac.rs.priprema.controller;

import com.singidunum.ac.rs.priprema.model.Kupac;
import com.singidunum.ac.rs.priprema.model.Kupovina;
import com.singidunum.ac.rs.priprema.model.Proizvod;
import com.singidunum.ac.rs.priprema.model.StavkaKupovine;
import com.singidunum.ac.rs.priprema.service.KorisnikService;
import com.singidunum.ac.rs.priprema.service.KupacService;
import com.singidunum.ac.rs.priprema.service.ProizvodService;
import com.singidunum.ac.rs.priprema.service.StavkaKupovineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class KupovinaController {

    @Autowired
    private KupacService kupacService;

    @Autowired
    private ProizvodService proizvodService;

    @Autowired
    private StavkaKupovineService stavkaKupovineService;

    @RequestMapping(path = "/kupovina",method = RequestMethod.POST)
    public ResponseEntity<Object> kreirajKupovinu(@RequestBody Kupovina kupovina, Authentication auth){
        String korisnickoIme = auth.getName();
        Kupac loggedKupac = kupacService.findByKorisnickoIme(korisnickoIme);

        if(loggedKupac != null){

            if(kupovina.getStavkeKupovina() != null){

                List<StavkaKupovine> stavkeKupovine = new ArrayList<>();

                for(StavkaKupovine s: kupovina.getStavkeKupovina()){
                    Proizvod proizvod = proizvodService.findById(s.getProizvod().getId());
                    if(proizvod != null){
                        stavkeKupovine.add(new StavkaKupovine(null,s.getKolicina(),proizvod,loggedKupac));
                    }else{
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                }

                stavkeKupovine.forEach(stavkaKupovine -> {
                    stavkaKupovineService.create(stavkaKupovine);
                });

                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
