package com.example.gestion_scolaire.services;



import com.example.gestion_scolaire.entities.Filiere;
import com.example.gestion_scolaire.repositories.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FiliereService {

    @Autowired
    private FiliereRepository filiereRepository;

    public List<Filiere> getAll() {
        return filiereRepository.findAll();
    }

    public Filiere getById(Long id) {
        return filiereRepository.findById(id).orElse(null);
    }

    @Transactional
    public Filiere save(Filiere filiere) {
        return filiereRepository.save(filiere);
    }

    @Transactional
    public void delete(Long id) {
        filiereRepository.deleteById(id);
    }
}