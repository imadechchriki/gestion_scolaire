package com.example.gestion_scolaire.services;



import com.example.gestion_scolaire.entities.Cours;
import com.example.gestion_scolaire.repositories.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoursService {

    @Autowired
    private CoursRepository coursRepository;

    public List<Cours> getAll() {
        return coursRepository.findAll();
    }

    public Cours getById(Long id) {
        return coursRepository.findById(id).orElse(null);
    }

    public List<Cours> getByFiliere(Long filiereId) {
        return coursRepository.findByFiliereId(filiereId);
    }

    @Transactional
    public Cours save(Cours cours) {
        return coursRepository.save(cours);
    }

    @Transactional
    public Cours update(Long id, Cours cours) {
        Cours existant = coursRepository.findById(id).orElse(null);
        if (existant != null) {
            existant.setCode(cours.getCode());
            existant.setIntitule(cours.getIntitule());
            existant.setFiliere(cours.getFiliere());
            return coursRepository.save(existant);
        }
        return null;
    }

    @Transactional
    public void delete(Long id) {
        coursRepository.deleteById(id);
    }
}
