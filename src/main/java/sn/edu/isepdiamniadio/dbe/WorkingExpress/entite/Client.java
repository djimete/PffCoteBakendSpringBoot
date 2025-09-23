package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClient", nullable = false)
    private Integer idClient;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "telephone", nullable = false, unique = true)
    private String telephone;

    //  Relation OneToOne avec Compte
    @OneToOne(cascade = CascadeType.ALL)
    private Compte compte;

    // Relation OneToMany avec Paiement
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paiement> paiements;

    // Relation OneToMany avec Demande
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Demande> demandes;

    // Relation ManyToMany avec Service
    @ManyToMany
    @JoinTable(
            name = "client_service",
            joinColumns = @JoinColumn(name = "idClient"),
            inverseJoinColumns = @JoinColumn(name = "idService")
    )
    private Set<ExpressService> services;

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", compte=" + (compte != null ? compte.getLogin() : "null") +
                ", paiements=" + paiements +
                ", demandes=" + demandes +
                ", services=" + services +
                '}';
    }
}
