package com.ey.springboot3security.service;

import com.ey.springboot3security.entity.Etudiant;

import java.time.LocalDate;
import java.util.List;

public interface IEtudiantService {
    Long addEtudiant(Etudiant etudiant);

    List<Etudiant> addAllEtudiant(List<Etudiant> liste);

    List<Etudiant> getAllEtudiants();

    Etudiant findById(Long id);

    void deleteById(Long id);

    Etudiant editEtudiant(Long id, Etudiant etudiant);
    void deleteAll();


    Etudiant findEtudiantByCin(Long cin);
    Etudiant findEtudiantByEmail(String em);
}
