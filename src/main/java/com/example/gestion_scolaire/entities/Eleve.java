package com.example.gestion_scolaire.entities;



import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "eleves")
public class Eleve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;

    @OneToOne(mappedBy = "eleve", cascade = CascadeType.ALL, orphanRemoval = true)
    private DossierAdministratif dossierAdministratif;

    @ManyToMany
    @JoinTable(
            name = "eleve_cours",
            joinColumns = @JoinColumn(name = "eleve_id"),
            inverseJoinColumns = @JoinColumn(name = "cours_id")
    )
    private Set<Cours> cours = new HashSet<>();


    public Eleve() {}

    public Eleve(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public DossierAdministratif getDossierAdministratif() {
        return dossierAdministratif;
    }

    public void setDossierAdministratif(DossierAdministratif dossierAdministratif) {
        this.dossierAdministratif = dossierAdministratif;
    }

    public Set<Cours> getCours() {
        return cours;
    }

    public void setCours(Set<Cours> cours) {
        this.cours = cours;
    }

    public void ajouterCours(Cours c) {
        this.cours.add(c);
        c.getEleves().add(this);
    }

    public void retirerCours(Cours c) {
        this.cours.remove(c);
        c.getEleves().remove(this);
    }
}