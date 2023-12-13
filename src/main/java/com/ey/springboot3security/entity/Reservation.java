package com.ey.springboot3security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    String idReservation;
    LocalDate anneUniversitaire;
    Boolean estValide;

    @ManyToOne
    Etudiant etudiant;



    @ManyToOne
    Chambre chambre;
    String statuReservation;
    

}
