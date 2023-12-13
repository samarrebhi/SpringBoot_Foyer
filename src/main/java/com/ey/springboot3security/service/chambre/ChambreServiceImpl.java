package com.ey.springboot3security.service.chambre;


import com.ey.springboot3security.entity.Bloc;
import com.ey.springboot3security.entity.Chambre;
import com.ey.springboot3security.entity.Reservation;
import com.ey.springboot3security.entity.TypeChambre;
import com.ey.springboot3security.repository.BlocRepo;
import com.ey.springboot3security.repository.ChambreRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ChambreServiceImpl implements IChambreService {
    ChambreRepo chambreRepository;
    BlocRepo blocRepository;

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public List<Chambre> addChambres(List<Chambre> chambres) {
        return chambreRepository.saveAll(chambres);
    }

    @Override
    public Chambre editChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public List<Chambre> findAll() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre findById(long id) {
        return chambreRepository.findById(id).get();
    }

    @Override
    public String deleteById(long id) {
        chambreRepository.deleteById(id);
        return "Chambre removed !! " + id;
    }

    @Override
    public void delete(Chambre b) {

    }

    @Override
    public Chambre findChambreByNumero(long numeroChambre) {
        return chambreRepository.findByNumeroChambre(numeroChambre);
    }

    @Override
    public List<Chambre> findChambreByType(TypeChambre typeChambre) {
        return chambreRepository.findByTypeChambre(typeChambre);
    }
    @Override
    public List<Chambre> findByBloc(Bloc bloc) {
        return chambreRepository.findByBloc(bloc);
    }

    @Override
    public Chambre getChambreByIdChambre(long idChambre){
        return chambreRepository.getChambreByIdChambre(idChambre);
    }

    @Override
    public List<Chambre> getChambresParNomBloc(String nomBloc) {
        Bloc b = blocRepository.getBlocByNomBloc(nomBloc);
        return chambreRepository.findByBloc(b) ;
    }
    @Override
    public long nbChambreParTypeEtBloc(TypeChambre type, long idBloc) {
        Bloc b = blocRepository.findById(idBloc).get();
        int c = chambreRepository.countChamberByTypeChambreAndBloc_IdBloc(type , idBloc);
        return c;
    }

    public void pourcentageChambreParTypeChambre() {
        String[] TypeChambre = new String[3];
        int nbSimple = chambreRepository.countChambreByTypeChambre(com.ey.springboot3security.entity.TypeChambre.SIMPLE);
        int nbDoube = chambreRepository.countChambreByTypeChambre(com.ey.springboot3security.entity.TypeChambre.DOUBLE);
        int nbTriple = chambreRepository.countChambreByTypeChambre(com.ey.springboot3security.entity.TypeChambre.TRIPLE);
        long nbChambres = chambreRepository.count();

        String message = String.format("Nombre Total des chambres : %d", nbChambres);
        String msg1 = String.format("Le pourcentage des chambres pour le type Simple est égal à %.2f%%", ((double) nbSimple / nbChambres * 100));
        String msg2 = String.format("Le pourcentage des chambres pour le type Double est égal à %.2f%%", ((double) nbDoube / nbChambres * 100));
        String msg3 = String.format("Le pourcentage des chambres pour le type Triple est égal à %.2f%%", ((double) nbTriple / nbChambres * 100));
        System.out.println(message);
        System.out.println(msg1);
        System.out.println(msg2);
        System.out.println(msg3);
    }
    @Override

    public long nbChambreParBloc(String nomBloc) {
        List<Bloc> blocs = blocRepository.findByNomBloc(nomBloc);

        long totalChambres = 0;

        for (Bloc bloc : blocs) {
            long nbChambresBloc = chambreRepository.countChambreByBloc_NomBloc(bloc.getNomBloc());
            System.out.println("Nombre de chambres dans le bloc " + bloc.getNomBloc() + " : " + nbChambresBloc);
            totalChambres += nbChambresBloc;
        }

        System.out.println("Nombre total de chambres pour tous les blocs : " + totalChambres);

        return totalChambres;
    }

    @Override
    public List<Chambre> getChambresNonReserveParNomFoyerEtTypeChambre(String nomFoyer, TypeChambre type) {
        return chambreRepository.findChambreByBlocFoyerNomFoyerAndTypeChambreAndReservations_Empty(nomFoyer,type);
    }

    @Override
    public Chambre UpdateChambreById(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public void affecterBlocAChambre(long idChamber, long idBloc) {
        Chambre chambre = chambreRepository.findById(idChamber).orElseThrow(() -> new EntityNotFoundException("Chambre not found"));
        Bloc bloc = blocRepository.findById(idBloc).orElseThrow(() -> new EntityNotFoundException("Bloc not found"));

        chambre.setBloc(bloc);
        chambreRepository.save(chambre);
    }

    @Override
    public List<Chambre> getChambresByBloc(String nomBloc) {
        return chambreRepository.findByBloc_NomBloc(nomBloc);
    }

}
