package com.example.gestion_scolaire.entities;



import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String intitule;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;

    @ManyToMany(mappedBy = "cours")
    private Set<Eleve> eleves = new HashSet<>();


    public Cours() {}

    public Cours(String code, String intitule) {
        this.code = code;
        this.intitule = intitule;
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

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Set<Eleve> getEleves() {
        return eleves;
    }

    public void setEleves(Set<Eleve> eleves) {
        this.eleves = eleves;
    }
}
