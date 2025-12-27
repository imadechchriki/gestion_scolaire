package com.example.gestion_scolaire.config;



import com.example.gestion_scolaire.entities.*;
import com.example.gestion_scolaire.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private FiliereRepository filiereRepository;

    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private EleveRepository eleveRepository;

    @Override
    public void run(String... args) throws Exception {
        // Créer des filières
        Filiere info = new Filiere("INFO", "Informatique");
        Filiere math = new Filiere("MATH", "Mathématiques");
        Filiere phy = new Filiere("PHY", "Physique");

        filiereRepository.save(info);
        filiereRepository.save(math);
        filiereRepository.save(phy);

        // Créer des cours
        Cours alg = new Cours("ALG101", "Algorithmique");
        alg.setFiliere(info);

        Cours prog = new Cours("PROG201", "Programmation Avancée");
        prog.setFiliere(info);

        Cours bd = new Cours("BD301", "Bases de Données");
        bd.setFiliere(info);

        Cours analyse = new Cours("MATH101", "Analyse Mathématique");
        analyse.setFiliere(math);

        Cours algebre = new Cours("MATH201", "Algèbre Linéaire");
        algebre.setFiliere(math);

        Cours mecanique = new Cours("PHY101", "Mécanique Classique");
        mecanique.setFiliere(phy);

        coursRepository.save(alg);
        coursRepository.save(prog);
        coursRepository.save(bd);
        coursRepository.save(analyse);
        coursRepository.save(algebre);
        coursRepository.save(mecanique);

        System.out.println("✅ Données initiales créées avec succès!");
        System.out.println("📚 " + filiereRepository.count() + " filières créées");
        System.out.println("📖 " + coursRepository.count() + " cours créés");
    }
}