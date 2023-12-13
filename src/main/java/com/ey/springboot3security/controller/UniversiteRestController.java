package com.ey.springboot3security.controller;


import com.ey.springboot3security.entity.Universite;
import com.ey.springboot3security.service.universite.IUniversiteService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/universites")
public class UniversiteRestController {

    IUniversiteService iUniversiteService;

    @GetMapping("findAllU")
    List<Universite> findAll(){
        return iUniversiteService.findAll();
    }

    @PostMapping("addUniversite")
    Universite addUniversite(@RequestBody Universite u){
        return iUniversiteService.addUniversite(u);
    }

    @PutMapping("updateUniversite")
    Universite updateUniversite(@RequestBody Universite u){
        return iUniversiteService.editUniversite(u);
    }



    @DeleteMapping("DeleteUniversite")
    void DeleteUniversite(@RequestBody Universite u){
        iUniversiteService.delete(u);
    }

    @GetMapping("universite/findByNom")
    public List<Universite>findByNomUniversite(@RequestParam("nomUniversite") String nomUniversite) {
        return iUniversiteService.findByNomUniversite(nomUniversite);
    }
}
