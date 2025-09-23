package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "prestataire")
public class Prestataire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrestataire", nullable = false)
    private Integer idPrestataire;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "telephone", nullable = false, unique = true)
    private String telephone;

    //  Relation OneToOne avec Compte (login comme clé primaire)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "login", referencedColumnName = "login")
    private Compte compte;

    //  Relation OneToMany avec Service
    @OneToMany(mappedBy = "prestataire", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExpressService> services = new ArrayList<>();

    //  Relation OneToMany avec Demande
    @OneToMany(mappedBy = "prestataire", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Demande> demandes = new ArrayList<>();

    @Override
    public String toString() {
        return "Prestataire{" +
                "idPrestataire=" + idPrestataire +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", compte=" + (compte != null ? compte.getLogin() : "null") +
                '}';
    }
}
