package com.ey.springboot3security.repository;


import com.ey.springboot3security.entity.Bloc;
import com.ey.springboot3security.entity.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoyerRepo  extends JpaRepository<Foyer, Long> {

    List<Foyer> findByBloc(Bloc bloc);
    List<Foyer>findByNomFoyer(String nomFoyer);

    Foyer findFoyerByNomFoyer(String nomFoyer);

}
