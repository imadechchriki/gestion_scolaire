package com.example.gestion_scolaire.entities;



import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dossiers_administratifs")
public class DossierAdministratif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroInscription;

    @Column(nullable = false)
    private LocalDate dateCreation;

    @OneToOne
    @JoinColumn(name = "eleve_id", nullable = false)
    private Eleve eleve;


    public DossierAdministratif() {
        this.dateCreation = LocalDate.now();
    }

    public DossierAdministratif(String numeroInscription, Eleve eleve) {
        this.numeroInscription = numeroInscription;
        this.dateCreation = LocalDate.now();
        this.eleve = eleve;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroInscription() {
        return numeroInscription;
    }

    public void setNumeroInscription(String numeroInscription) {
        this.numeroInscription = numeroInscription;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }
}