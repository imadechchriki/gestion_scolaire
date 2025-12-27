package com.example.gestion_scolaire.repositories;




import com.example.gestion_scolaire.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EleveRepository extends JpaRepository<Eleve, Long> {
    List<Eleve> findByFiliereId(Long filiereId);
}