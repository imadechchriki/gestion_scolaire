package com.example.gestion_scolaire.repositories;



import com.example.gestion_scolaire.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    Filiere findByCode(String code);
}