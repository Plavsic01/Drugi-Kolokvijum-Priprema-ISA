package com.singidunum.ac.rs.priprema.controller;

import com.singidunum.ac.rs.priprema.dto.KorisnikDTO;
import com.singidunum.ac.rs.priprema.dto.KorisnikPravoPristupaDTO;
import com.singidunum.ac.rs.priprema.dto.PravoPristupaDTO;
import com.singidunum.ac.rs.priprema.model.Korisnik;
import com.singidunum.ac.rs.priprema.model.KorisnikPravoPristupa;
import com.singidunum.ac.rs.priprema.model.PravoPristupa;
import com.singidunum.ac.rs.priprema.service.KorisnikService;
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

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/api")
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private PravoPristupaService pravoPristupaService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(path = "/korisnici",method = RequestMethod.GET)
    public ResponseEntity<List<KorisnikDTO>> dobaviSve(Principal principal){
        System.out.println(principal);
        List<KorisnikDTO> korisnici = new ArrayList<>();

        for(Korisnik k: korisnikService.findAll()){
            Set<KorisnikPravoPristupaDTO> korisnikPravoPristupaDTO = new HashSet<>();
            for(KorisnikPravoPristupa p:k.getKorisnikPravoPristupa()){
                korisnikPravoPristupaDTO.add(new KorisnikPravoPristupaDTO(p.getId(),null,
                        new PravoPristupaDTO(p.getPravoPristupa().getId(),p.getPravoPristupa().getNaziv())));
            }
            korisnici.add(new KorisnikDTO(k.getId(),k.getKorisnickoIme(),null,korisnikPravoPristupaDTO));
        }
        return new ResponseEntity<List<KorisnikDTO>>(korisnici,HttpStatus.OK);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @RequestMapping(path = "/korisnici/{id}",method = RequestMethod.GET)
    public ResponseEntity<KorisnikDTO> dobaviJednog(@PathVariable Long id){
        Korisnik k = korisnikService.findById(id);
        if(k != null){
            Set<KorisnikPravoPristupaDTO> korisnikPravoPristupaDTO = new HashSet<>();
            for(KorisnikPravoPristupa p:k.getKorisnikPravoPristupa()){
                korisnikPravoPristupaDTO.add(new KorisnikPravoPristupaDTO(p.getId(),null,
                        new PravoPristupaDTO(p.getPravoPristupa().getId(),p.getPravoPristupa().getNaziv())));
            }
            KorisnikDTO korisnikDTO = new KorisnikDTO(k.getId(),k.getKorisnickoIme(),null,korisnikPravoPristupaDTO);
            return new ResponseEntity<KorisnikDTO>(korisnikDTO,HttpStatus.OK);
        }

        return new ResponseEntity<KorisnikDTO>(HttpStatus.NOT_FOUND);
    }


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/korisnici",method = RequestMethod.POST)
    public ResponseEntity<KorisnikDTO> kreiraj(@RequestBody KorisnikDTO korisnik){
        String encodedLozinka = passwordEncoder.encode(korisnik.getLozinka());
        Korisnik k = new Korisnik(null,korisnik.getKorisnickoIme(),encodedLozinka);
        k = korisnikService.create(k);

        KorisnikDTO korisnikDTO = new KorisnikDTO(k.getId(),k.getKorisnickoIme(),null);

        return new ResponseEntity<KorisnikDTO>(korisnikDTO,HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/korisnici/{id}",method = RequestMethod.PUT)
    public ResponseEntity<KorisnikDTO> izmeni(@PathVariable Long id,@RequestBody KorisnikDTO korisnik){
        Korisnik k = korisnikService.findById(id);
        if(k != null){

            Set<KorisnikPravoPristupa> korisnikPravoPristupa = new HashSet<>();

            if(korisnik.getKorisnikPravoPristupa() != null){

                for(KorisnikPravoPristupaDTO kp:korisnik.getKorisnikPravoPristupa()){
                    PravoPristupa pravoPristupa = pravoPristupaService.findById(kp.getPravoPristupa().getId());
                    if(pravoPristupa != null){
                        korisnikPravoPristupa.add(new KorisnikPravoPristupa(kp.getId(),k,pravoPristupa));
                    }else{
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                }
                k.setKorisnikPravoPristupa(korisnikPravoPristupa);
            }

            String encodedLozinka = passwordEncoder.encode(korisnik.getLozinka());

            k.setKorisnickoIme(korisnik.getKorisnickoIme());
            k.setLozinka(encodedLozinka);

            k = korisnikService.update(k);

            Set<KorisnikPravoPristupaDTO> korisnikPravoPristupaDTO = new HashSet<>();

            for(KorisnikPravoPristupa kpp:k.getKorisnikPravoPristupa()){
                korisnikPravoPristupaDTO.add(new KorisnikPravoPristupaDTO(kpp.getId(),
                       null,
                        new PravoPristupaDTO(kpp.getPravoPristupa().getId(),kpp.getPravoPristupa().getNaziv())));
            }

            KorisnikDTO korisnikDTO = new KorisnikDTO(k.getId(),k.getKorisnickoIme(),null,
                    korisnikPravoPristupaDTO);

            return new ResponseEntity<KorisnikDTO>(korisnikDTO,HttpStatus.OK);
        }
        return new ResponseEntity<KorisnikDTO>(HttpStatus.NOT_FOUND);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/korisnici/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> ukloni(@PathVariable Long id){
        Korisnik k = korisnikService.findById(id);
        if(k != null){
            korisnikService.delete(k.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }




}
