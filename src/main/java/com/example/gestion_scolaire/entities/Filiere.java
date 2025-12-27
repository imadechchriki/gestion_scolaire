package com.example.gestion_scolaire.entities;



import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "filieres")
public class Filiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String nom;

    @OneToMany(mappedBy = "filiere", cascade = CascadeType.ALL)
    private List<Eleve> eleves = new ArrayList<>();

    @OneToMany(mappedBy = "filiere", cascade = CascadeType.ALL)
    private List<Cours> cours = new ArrayList<>();


    public Filiere() {}

    public Filiere(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Eleve> getEleves() {
        return eleves;
    }

    public void setEleves(List<Eleve> eleves) {
        this.eleves = eleves;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }
}