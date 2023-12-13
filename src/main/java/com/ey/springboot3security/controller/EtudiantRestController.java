package com.ey.springboot3security.controller;

import com.ey.springboot3security.entity.Etudiant;
import com.ey.springboot3security.service.user.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Etudiants")
public class EtudiantRestController {
    @Autowired
    EtudiantService Service;

    @PostMapping("/addEtudiant")
    public Long addEtudiant(@RequestBody Etudiant e) {return Service.addEtudiant(e);}
    @PostMapping("/addEtudiants")
    public List<Etudiant> addEtudiants(@RequestBody List<Etudiant> liste)
    {
        return Service.addAllEtudiant(liste);
    }

    @DeleteMapping("/deletebyid/{id}")
    void DeleteEtudiantByID(@PathVariable("id") Long id){
        Service.deleteById(id);
    }
    @DeleteMapping("/deleteall") String DeleteAll(){Service.deleteAll();return "all students are deleted";
    }
    @PutMapping("updateEtudiant/{id}")
    Etudiant updateEtudiant(@PathVariable("id") Long id ,@RequestBody Etudiant e){
        return  Service.editEtudiant(id,e);
    }


    @GetMapping("/getEtudiants")
    public ResponseEntity<List<Etudiant>> getAllEtudiants(){
        List<Etudiant> liste=Service.getAllEtudiants();
        return new ResponseEntity<List<Etudiant>>(liste, HttpStatus.OK);
    }
    @GetMapping("/getby/{id}") Etudiant findById(@PathVariable("id") Long id){ return Service.findById(id);}



    @GetMapping("/getbycin/{cin}")
    public ResponseEntity<Etudiant> findEtudiantBycin(@PathVariable("cin") Long cin){
        Etudiant l=Service.findEtudiantByCin(cin);
        return ResponseEntity.ok(l);}


    }













