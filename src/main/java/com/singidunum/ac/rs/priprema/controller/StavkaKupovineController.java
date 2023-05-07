package com.singidunum.ac.rs.priprema.controller;

import com.singidunum.ac.rs.priprema.dto.KupacDTO;
import com.singidunum.ac.rs.priprema.dto.ProizvodDTO;
import com.singidunum.ac.rs.priprema.dto.StavkaKupovineDTO;
import com.singidunum.ac.rs.priprema.model.Kupac;
import com.singidunum.ac.rs.priprema.model.Proizvod;
import com.singidunum.ac.rs.priprema.model.StavkaKupovine;
import com.singidunum.ac.rs.priprema.service.KupacService;
import com.singidunum.ac.rs.priprema.service.ProizvodService;
import com.singidunum.ac.rs.priprema.service.StavkaKupovineService;
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
public class StavkaKupovineController {

    @Autowired
    private StavkaKupovineService stavkaKupovineService;

    @Autowired
    private ProizvodService proizvodService;

    @Autowired
    private KupacService kupacService;

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(path = "/stavkeKupovine",method = RequestMethod.GET)
    public ResponseEntity<List<StavkaKupovineDTO>> dobaviSve(){
        List<StavkaKupovineDTO> stavkeKupovine = new ArrayList<>();
        for(StavkaKupovine p: stavkaKupovineService.findAll()){
            stavkeKupovine.add(new StavkaKupovineDTO(p.getId(),p.getKolicina(),
                    new ProizvodDTO(p.getProizvod().getId(),p.getProizvod().getNaziv(),p.getProizvod().getCena()),
                    new KupacDTO(p.getKupac().getId(),p.getKupac().getKorisnickoIme(),null,p.getKupac().getAdresa())));
        }
        return new ResponseEntity<List<StavkaKupovineDTO>>(stavkeKupovine,HttpStatus.OK);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(path = "/stavkeKupovine/{id}",method = RequestMethod.GET)
    public ResponseEntity<StavkaKupovineDTO> dobaviJednog(@PathVariable Long id){
        StavkaKupovine s = stavkaKupovineService.findById(id);
        if(s != null){
               StavkaKupovineDTO stavkaKupovineDTO = new StavkaKupovineDTO(s.getId(),s.getKolicina(),
                       new ProizvodDTO(s.getProizvod().getId(),s.getProizvod().getNaziv(),s.getProizvod().getCena()),
                       new KupacDTO(s.getKupac().getId(),s.getKupac().getKorisnickoIme(),null,s.getKupac().getAdresa()));

               return new ResponseEntity<StavkaKupovineDTO>(stavkaKupovineDTO,HttpStatus.OK);
            }
        return new ResponseEntity<StavkaKupovineDTO>(HttpStatus.NOT_FOUND);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/stavkeKupovine",method = RequestMethod.POST)
    public ResponseEntity<StavkaKupovineDTO> kreiraj(@RequestBody StavkaKupovineDTO stavkaKupovine){

        Proizvod p = proizvodService.findById(stavkaKupovine.getProizvod().getId());
        Kupac k = kupacService.findById(stavkaKupovine.getKupac().getId());

        if(p != null && k != null){
            StavkaKupovine sk = new StavkaKupovine(null,stavkaKupovine.getKolicina(),p,k);

            sk = stavkaKupovineService.create(sk);

            StavkaKupovineDTO stavkaKupovineDTO = new StavkaKupovineDTO(sk.getId(),sk.getKolicina(),
                    new ProizvodDTO(sk.getProizvod().getId(),sk.getProizvod().getNaziv(),sk.getProizvod().getCena()),
                    new KupacDTO(sk.getKupac().getId(),sk.getKupac().getKorisnickoIme(),null,sk.getKupac().getAdresa()));

            return new ResponseEntity<StavkaKupovineDTO>(stavkaKupovineDTO,HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/stavkeKupovine/{id}",method = RequestMethod.PUT)
    public ResponseEntity<StavkaKupovineDTO> izmeni(@PathVariable Long id, @RequestBody StavkaKupovineDTO stavkaKupovine){
        StavkaKupovine sk = stavkaKupovineService.findById(id);
        if(sk != null){

            sk.setKolicina(stavkaKupovine.getKolicina());

            if(stavkaKupovine.getProizvod() != null){
                Proizvod p = proizvodService.findById(stavkaKupovine.getProizvod().getId());
                if(p != null){
                    sk.setProizvod(p);
                }else{
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }

            if(stavkaKupovine.getKupac() != null){
                Kupac k = kupacService.findById(stavkaKupovine.getKupac().getId());
                if(k != null){
                    sk.setKupac(k);
                }else{
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }

            sk = stavkaKupovineService.update(sk);

            StavkaKupovineDTO stavkaKupovineDTO = new StavkaKupovineDTO(sk.getId(),sk.getKolicina(),
                    new ProizvodDTO(sk.getProizvod().getId(),sk.getProizvod().getNaziv(),sk.getProizvod().getCena()),
                    new KupacDTO(sk.getKupac().getId(),sk.getKupac().getKorisnickoIme(),null,sk.getKupac().getAdresa()));

            return new ResponseEntity<StavkaKupovineDTO>(stavkaKupovineDTO,HttpStatus.OK);
        }
        return new ResponseEntity<StavkaKupovineDTO>(HttpStatus.NOT_FOUND);
    }


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/stavkeKupovine/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> ukloni(@PathVariable Long id){
        StavkaKupovine sk = stavkaKupovineService.findById(id);
        if(sk != null){
            stavkaKupovineService.delete(sk.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }


}
