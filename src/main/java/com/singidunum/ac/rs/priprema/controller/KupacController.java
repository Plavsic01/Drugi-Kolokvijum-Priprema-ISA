package com.singidunum.ac.rs.priprema.controller;

import com.singidunum.ac.rs.priprema.dto.*;
import com.singidunum.ac.rs.priprema.model.KorisnikPravoPristupa;
import com.singidunum.ac.rs.priprema.model.Kupac;
import com.singidunum.ac.rs.priprema.model.PravoPristupa;
import com.singidunum.ac.rs.priprema.model.StavkaKupovine;
import com.singidunum.ac.rs.priprema.service.KupacService;
import com.singidunum.ac.rs.priprema.service.PravoPristupaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/api")
public class KupacController {

    @Autowired
    private KupacService kupacService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PravoPristupaService pravoPristupaService;


    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(path = "/kupci",method = RequestMethod.GET)
    public ResponseEntity<List<KupacDTO>> dobaviSve(){
        List<KupacDTO> kupci = new ArrayList<>();
        Set<StavkaKupovineDTO> stavkaKupovineDTO = new HashSet<>();

        for(Kupac k: kupacService.findAll()){
            Set<KorisnikPravoPristupaDTO> korisnikPravoPristupaDTO = new HashSet<>();

            for(KorisnikPravoPristupa p:k.getKorisnikPravoPristupa()){
                korisnikPravoPristupaDTO.add(new KorisnikPravoPristupaDTO(p.getId(),null,
                        new PravoPristupaDTO(p.getPravoPristupa().getId(),p.getPravoPristupa().getNaziv())));
            }
            for(StavkaKupovine s:k.getStavkaKupovine()){
                stavkaKupovineDTO.add(new StavkaKupovineDTO(s.getId(),s.getKolicina(),
                        new ProizvodDTO(s.getProizvod().getId(),s.getProizvod().getNaziv(),s.getProizvod().getCena()),
                        null));
            }
            kupci.add(new KupacDTO(k.getId(),k.getKorisnickoIme(),null,korisnikPravoPristupaDTO,k.getAdresa(),
                    stavkaKupovineDTO));
        }
        return new ResponseEntity<List<KupacDTO>>(kupci, HttpStatus.OK);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(path = "/kupci/{id}",method = RequestMethod.GET)
    public ResponseEntity<KupacDTO> dobaviJednog(@PathVariable Long id){
        Kupac k = kupacService.findById(id);
        if(k != null){
            Set<KorisnikPravoPristupaDTO> korisnikPravoPristupaDTO = new HashSet<>();
            Set<StavkaKupovineDTO> stavkaKupovineDTO = new HashSet<>();

            for(KorisnikPravoPristupa p:k.getKorisnikPravoPristupa()){
                korisnikPravoPristupaDTO.add(new KorisnikPravoPristupaDTO(p.getId(),null,
                        new PravoPristupaDTO(p.getPravoPristupa().getId(),p.getPravoPristupa().getNaziv())));
            }

            for(StavkaKupovine s:k.getStavkaKupovine()){
                stavkaKupovineDTO.add(new StavkaKupovineDTO(s.getId(),s.getKolicina(),
                        new ProizvodDTO(s.getProizvod().getId(),s.getProizvod().getNaziv(),s.getProizvod().getCena()),
                        null));
            }

            KupacDTO kupacDTO = new KupacDTO(k.getId(),k.getKorisnickoIme(),null,korisnikPravoPristupaDTO,
                    k.getAdresa(),stavkaKupovineDTO);
            return new ResponseEntity<KupacDTO>(kupacDTO,HttpStatus.OK);
        }

        return new ResponseEntity<KupacDTO>(HttpStatus.NOT_FOUND);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/kupci",method = RequestMethod.POST)
    public ResponseEntity<KupacDTO> kreiraj(@RequestBody KupacDTO kupac){

        String encodedLozinka = passwordEncoder.encode(kupac.getLozinka());
        Kupac k = new Kupac(kupac.getId(),kupac.getKorisnickoIme(),encodedLozinka,kupac.getAdresa(),null);

        k = kupacService.create(k);


        KupacDTO kupacDTO = new KupacDTO(k.getId(),k.getKorisnickoIme(),null,null,k.getAdresa());

        return new ResponseEntity<KupacDTO>(kupacDTO,HttpStatus.CREATED);
    }


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/kupci/{id}",method = RequestMethod.PUT)
    public ResponseEntity<KupacDTO> izmeni(@PathVariable Long id, @RequestBody KupacDTO kupac){
        Kupac k = kupacService.findById(id);
        if(k != null){

            Set<KorisnikPravoPristupa> korisnikPravoPristupa = new HashSet<>();

            if(kupac.getKorisnikPravoPristupa() != null){

                for(KorisnikPravoPristupaDTO kp:kupac.getKorisnikPravoPristupa()){
                    PravoPristupa pravoPristupa = pravoPristupaService.findById(kp.getPravoPristupa().getId());
                    if(pravoPristupa != null){
                        korisnikPravoPristupa.add(new KorisnikPravoPristupa(kp.getId(),k,pravoPristupa));
                    }else{
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                }

                k.setKorisnikPravoPristupa(korisnikPravoPristupa);
            }

            String encodedLozinka = passwordEncoder.encode(kupac.getLozinka());

            k.setKorisnickoIme(kupac.getKorisnickoIme());
            k.setLozinka(encodedLozinka);
            k.setAdresa(kupac.getAdresa());

            k = kupacService.update(k);

            Set<KorisnikPravoPristupaDTO> korisnikPravoPristupaDTO = new HashSet<>();

            for(KorisnikPravoPristupa kpp:k.getKorisnikPravoPristupa()){
                korisnikPravoPristupaDTO.add(new KorisnikPravoPristupaDTO(kpp.getId(),
                        null,
                        new PravoPristupaDTO(kpp.getPravoPristupa().getId(),kpp.getPravoPristupa().getNaziv())));
            }

            KupacDTO kupacDTO = new KupacDTO(k.getId(),k.getKorisnickoIme(),null,korisnikPravoPristupaDTO
                    ,k.getAdresa());
            return new ResponseEntity<>(kupacDTO,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/kupci/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> ukloni(@PathVariable Long id){
        Kupac k = kupacService.findById(id);
        if(k != null){
            kupacService.delete(k.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }


}
