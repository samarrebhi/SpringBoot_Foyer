package com.ey.springboot3security.repository;

import com.ey.springboot3security.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {

    Etudiant findEtudiantByCin(Long cin);
    Etudiant findEtudiantByEmail(String em);
}
