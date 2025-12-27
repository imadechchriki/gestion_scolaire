package com.example.gestion_scolaire.services;



import com.example.gestion_scolaire.entities.*;
import com.example.gestion_scolaire.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EleveService {

    @Autowired
    private EleveRepository eleveRepository;

    @Autowired
    private DossierAdministratifRepository dossierRepository;

    @Autowired
    private CoursRepository coursRepository;

    public List<Eleve> getAll() {
        return eleveRepository.findAll();
    }

    public Eleve getById(Long id) {
        return eleveRepository.findById(id).orElse(null);
    }

    public List<Eleve> getByFiliere(Long filiereId) {
        return eleveRepository.findByFiliereId(filiereId);
    }

    @Transactional
    public Eleve save(Eleve eleve) {

        Eleve savedEleve = eleveRepository.save(eleve);


        if (savedEleve.getDossierAdministratif() == null) {
            DossierAdministratif dossier = new DossierAdministratif();
            dossier.setEleve(savedEleve);
            dossier.setDateCreation(LocalDate.now());


            String numeroInscription = genererNumeroInscription(savedEleve);
            dossier.setNumeroInscription(numeroInscription);

            dossierRepository.save(dossier);
            savedEleve.setDossierAdministratif(dossier);
        }

        return savedEleve;
    }

    private String genererNumeroInscription(Eleve eleve) {
        String codeFiliere = eleve.getFiliere() != null ?
                eleve.getFiliere().getCode() : "SANS";
        int annee = LocalDate.now().getYear();
        return codeFiliere + "-" + annee + "-" + eleve.getId();
    }

    @Transactional
    public Eleve update(Long id, Eleve eleve) {
        Eleve existant = eleveRepository.findById(id).orElse(null);
        if (existant != null) {
            existant.setNom(eleve.getNom());
            existant.setPrenom(eleve.getPrenom());
            existant.setFiliere(eleve.getFiliere());


            if (existant.getDossierAdministratif() != null) {
                String nouveauNumero = genererNumeroInscription(existant);
                existant.getDossierAdministratif().setNumeroInscription(nouveauNumero);
            }

            return eleveRepository.save(existant);
        }
        return null;
    }

    @Transactional
    public void delete(Long id) {
        eleveRepository.deleteById(id);
    }

    @Transactional
    public void inscrireCours(Long eleveId, Long coursId) {
        Eleve eleve = eleveRepository.findById(eleveId).orElse(null);
        Cours cours = coursRepository.findById(coursId).orElse(null);

        if (eleve != null && cours != null) {
            eleve.ajouterCours(cours);
            eleveRepository.save(eleve);
        }
    }

    @Transactional
    public void desinscrireCours(Long eleveId, Long coursId) {
        Eleve eleve = eleveRepository.findById(eleveId).orElse(null);
        Cours cours = coursRepository.findById(coursId).orElse(null);

        if (eleve != null && cours != null) {
            eleve.retirerCours(cours);
            eleveRepository.save(eleve);
        }
    }
}