package com.ey.springboot3security.service.bloc;

import com.ey.springboot3security.entity.Bloc;
import com.ey.springboot3security.entity.Chambre;

import java.util.List;

public interface IBlocService {

    Bloc addBloc(Bloc b );


    List<Bloc> addBlocs(List<Bloc>blocs);
    Bloc editBloc (Bloc b);

    List<Bloc> findAll();

    Bloc findById(long id);

    void deleteById(long id);

    void delete(Bloc b);

    List<Bloc> findByNomBloc(String nomBloc);
    List<Bloc> findByCapaciteBloc(int capaciteBloc);
    List<Bloc> findByNomBlocAndCapaciteBloc(String nomBloc, int capaciteBloc);
    List<Bloc> findByNomBlocIgnoreCase(String nomBloc);
    List<Bloc> findByCapaciteBlocGreaterThan(int capacite);
    List<Bloc> findByNomBlocContaining(String subString);
    List<Bloc> findAllByOrderByNomBlocAsc();
    List<Bloc> findByNomBlocOrCapaciteBloc(String nomBloc, int capaciteBloc);


    Bloc affecterChambresABloc(List<Integer> numChambre, String nomBloc);

    Bloc desaffecterChambreABloc(long idBloc);

    Bloc affecterBlocAFoyer(String nomBloc, String nomFoyer);
}
