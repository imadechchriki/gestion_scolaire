package com.example.gestion_scolaire.repositories;




import com.example.gestion_scolaire.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DossierAdministratifRepository extends JpaRepository<DossierAdministratif, Long> {
    DossierAdministratif findByEleveId(Long eleveId);
}