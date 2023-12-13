package com.ey.springboot3security.controller;

import com.ey.springboot3security.entity.Reservation;
import com.ey.springboot3security.service.reservation.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("reservation")
public class ReservationRestController {
    IReservationService ireservationservice;


    @GetMapping("/getByAnneeUniversitaire/{deb}/{fin}")
    public List<Reservation> getReservationbyAnneeUniversitaire(
            @PathVariable("deb") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate debutAnnee,
            @PathVariable("fin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate finAnnee
    ) {
        return ireservationservice.getReservationParAnneeUniversitaire(debutAnnee, finAnnee);
    }

    @GetMapping("/getAll")
    public List<Reservation> getAllReservations() {
        return ireservationservice.getAll();
    }

    @GetMapping("/getById/{id}")
    public Reservation getReservationById(@PathVariable("id") String id) {
        return ireservationservice.getById(id);
    }

    @PostMapping("/add/{numChambre}/{cin}")
    public ResponseEntity<Reservation>
    ajouterReservationEtAssignerAChambreEtAEtudiant(
            @PathVariable("numChambre") long numChambre,
            @PathVariable("cin") long cin
    ) {
        return ResponseEntity.ok(ireservationservice.ajouterReservationEtAssignerAChambreEtAEtudiant(numChambre, cin));
    }

    @PostMapping("/annulerReservation/{idReservation}")
    public ResponseEntity<String>
    annulerReservation(
            @PathVariable("idReservation") String idReservation
    ) {
        return ireservationservice.annulerReservation(idReservation);
    }

    @PutMapping("/validate")
    public ResponseEntity<Reservation> validate(@RequestParam String idReservation){
        return ResponseEntity.ok(ireservationservice.validate(idReservation));
    }

    @PutMapping("/refuse")
    public ResponseEntity<Reservation> refuse(@RequestParam String idReservation){
        return ResponseEntity.ok(ireservationservice.refuse(idReservation));
    }

}
