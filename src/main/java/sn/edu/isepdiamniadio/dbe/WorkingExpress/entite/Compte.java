package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "compte")
public class Compte {

    @Id
    @Column(name = "idClient") // même identifiant que le Client
    private Integer idClient;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "motPasse", nullable = false)
    private String motPasse;

    // Relation OneToOne avec Client (clé partagée)
    @OneToOne
    @MapsId   // indique que l’id de Compte est celui du Client
    @JoinColumn(name = "idClient")
    private sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Client client;
    // Relation OneToOne bidirectionnelle avec Prestataire
    @OneToOne(mappedBy = "compte", cascade = CascadeType.ALL, orphanRemoval = true)
    private Prestataire prestataire;


    @Override
    public String toString() {
        return "Compte{" +
                "idClient=" + idClient +
                ", login='" + login + '\'' +
                ", motPasse='" + motPasse + '\'' +
                ", prestataire=" + (prestataire != null ? prestataire.getNom() + " " + prestataire.getPrenom() : "null") +
                '}';
    }
}
