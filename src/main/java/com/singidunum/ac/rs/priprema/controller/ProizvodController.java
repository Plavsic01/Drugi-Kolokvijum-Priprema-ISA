package com.singidunum.ac.rs.priprema.controller;

import com.singidunum.ac.rs.priprema.dto.*;
import com.singidunum.ac.rs.priprema.model.Korisnik;
import com.singidunum.ac.rs.priprema.model.KorisnikPravoPristupa;
import com.singidunum.ac.rs.priprema.model.Kupac;
import com.singidunum.ac.rs.priprema.model.Proizvod;
import com.singidunum.ac.rs.priprema.service.ProizvodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class ProizvodController {

    @Autowired
    private ProizvodService proizvodService;

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(path = "/proizvodi",method = RequestMethod.GET)
    public ResponseEntity<List<ProizvodDTO>> dobaviSve(){
        List<ProizvodDTO> proizvodi = new ArrayList<>();
        for(Proizvod p: proizvodService.findAll()){
            proizvodi.add(new ProizvodDTO(p.getId(),p.getNaziv(),p.getCena()));
        }
        return new ResponseEntity<List<ProizvodDTO>>(proizvodi, HttpStatus.OK);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(path = "/proizvodi/{id}",method = RequestMethod.GET)
    public ResponseEntity<ProizvodDTO> dobaviJednog(@PathVariable Long id){
        Proizvod p = proizvodService.findById(id);
        if(p != null){
            ProizvodDTO proizvodDTO = new ProizvodDTO(p.getId(),p.getNaziv(),p.getCena());
            return new ResponseEntity<ProizvodDTO>(proizvodDTO, HttpStatus.OK);
        }
        return new ResponseEntity<ProizvodDTO>(HttpStatus.NOT_FOUND);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/proizvodi",method = RequestMethod.POST)
    public ResponseEntity<ProizvodDTO> kreiraj(@RequestBody ProizvodDTO proizvod){

        Proizvod p = new Proizvod(null,proizvod.getNaziv(),proizvod.getCena());
        p = proizvodService.create(p);

        ProizvodDTO proizvodDTO = new ProizvodDTO(p.getId(),p.getNaziv(),p.getCena());

        return new ResponseEntity<ProizvodDTO>(proizvodDTO,HttpStatus.CREATED);
    }


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/proizvodi/{id}",method = RequestMethod.PUT)
    public ResponseEntity<ProizvodDTO> izmeni(@PathVariable Long id, @RequestBody ProizvodDTO proizvod){
        Proizvod p = proizvodService.findById(id);
        if(p != null){
            p.setNaziv(proizvod.getNaziv());
            p.setCena(proizvod.getCena());
            p = proizvodService.update(p);

            ProizvodDTO proizvodDTO = new ProizvodDTO(p.getId(),p.getNaziv(),p.getCena());

            return new ResponseEntity<ProizvodDTO>(proizvodDTO,HttpStatus.OK);
        }
        return new ResponseEntity<ProizvodDTO>(HttpStatus.NOT_FOUND);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/proizvodi/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> ukloni(@PathVariable Long id){
        Proizvod p = proizvodService.findById(id);
        if(p != null){
            proizvodService.delete(p.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }



}
