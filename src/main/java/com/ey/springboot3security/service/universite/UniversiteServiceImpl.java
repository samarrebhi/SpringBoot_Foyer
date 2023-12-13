package com.ey.springboot3security.service.universite;


import com.ey.springboot3security.entity.Foyer;
import com.ey.springboot3security.entity.Universite;
import com.ey.springboot3security.repository.FoyerRepo;
import com.ey.springboot3security.repository.UniversiteRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class UniversiteServiceImpl implements IUniversiteService {

    UniversiteRepo universiteRepository;

    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public List<Universite> addUniversites(List<Universite> universites) {
        return universiteRepository.saveAll(universites);
    }

    @Override
    public Universite editUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public List<Universite> findAll() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite findById(long id) {
        return universiteRepository.findById(id).get();
    }

    @Override
    public void deleteById(long id) {
        universiteRepository.deleteById(id);
    }

    @Override
    public void delete(Universite u) {
        universiteRepository.delete(u);
    }
    @Override
    public List<Universite>findByNomUniversite(String nomUniversite)
    {
        return universiteRepository.findByNomUniversite(nomUniversite);
    }

}
