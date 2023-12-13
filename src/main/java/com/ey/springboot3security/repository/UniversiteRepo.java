package com.ey.springboot3security.repository;


import com.ey.springboot3security.entity.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversiteRepo extends JpaRepository<Universite,Long> {

    List<Universite> findByNomUniversite(String nomUniversite);
}
