package com.ey.springboot3security.service.reservation;


import com.ey.springboot3security.entity.Reservation;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IReservationService {


    Reservation add(Reservation reservation);
    Reservation ajouterReservationEtAssignerAChambreEtAEtudiant(long numChambre, long cin);
    List<Reservation> getReservationParAnneeUniversitaire(LocalDate debutAnnee, LocalDate finAnnee);
    List<Reservation> getAll();
    Reservation getById(String id);
    Reservation validate(String idRes);
    Reservation refuse(String idRes);

    ResponseEntity<String> annulerReservation(String idReservation);
}
